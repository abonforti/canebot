package org.telegram.cane.processors.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.telegram.cane.constants.CaneConstants;
import org.telegram.cane.core.DBUtils;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.ForceReply;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.PhotoSize;
import io.github.nixtabyte.telegram.jtelebot.response.json.ReplyKeyboardMarkup;
import io.github.nixtabyte.telegram.jtelebot.response.json.TelegramResponse;
import io.github.nixtabyte.telegram.jtelebot.response.json.User;

public class ArbitroProcessor extends AbstractMessageProcessor implements MessageProcessor {
    private static final Logger LOG = Logger.getLogger(ArbitroProcessor.class);

    private List<String> arbitroCommands = new ArrayList<>();
    private final int imageIndex = 2;
    private final List<Integer> numColonne = Arrays.asList(2, 3, 5);

    private final String FILE_DOWNLOAD_URL = "https://api.telegram.org/file/bot" + CaneConstants.BOT_ID + "/";
    private final String REPLY_ALIAS = "Qual è l'alias da associare?";
    private String currentImgId;

    public ArbitroProcessor() {
        // Trying to parse aliases from table
        initAliases();
    }

    @Override
    public boolean process(final Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        boolean found = false;
        // Checking whether it's a text message or a photo message
        if (StringUtils.isNotEmpty(message.getText())) {
            // Checking whehter it's a reply message or a simple text message
            if (detectReplyAliasMessage(message)) {
                String alias = message.getText();
                updateImageAliases(alias, currentImgId);
                replyTextMessage("Thanks boss. Alias '" + alias + "' set for imgId: " + currentImgId, message, requestHandler);
                return true;

            } else {
                if (StringUtils.startsWithIgnoreCase(message.getText(), "/getAllImageKeys")) {

                    initAliases();

                    ReplyKeyboardMarkup key = new ReplyKeyboardMarkup();
                    int righe = retrieveProperRowNum();
                    int col = arbitroCommands.size() / righe;
                    String[][] keyboard = new String[righe][col];
                    int riga = 0;
                    for (int i = 0; i < arbitroCommands.size(); i = i + col) {
                        for (int j = 0; j < col; j++) {
                            keyboard[riga][j] = arbitroCommands.get(i + j);
                        }
                        riga++;
                    }

                    key.setKeyboard(keyboard);
                    key.setSelective(Boolean.FALSE);
                    key.setOneTimeKeyboard(Boolean.TRUE);
                    key.setResizeKeyboard(Boolean.TRUE);

                    TelegramRequest request2 = TelegramRequestFactory.createSendMessageRequest(message.getChat().getId(), "Scegli la keyword!!!", true, null, key);
                    requestHandler.sendRequest(request2);
                    found = true;
                } else {
                    found = processSimpleTextMessage(message, requestHandler);
                }
            }

        } else {
            if (message.getPhoto() != null && message.getChat() instanceof User) {
                PhotoSize[] photos = message.getPhoto();

                PhotoSize photo;
                if (photos.length > imageIndex) {
                    photo = photos[imageIndex];
                } else {
                    photo = photos[0];
                }

                LOG.info("Image sent detected with fileId: " + photo.getFileId() + " " + photo.getFilePath() + ", downloading it...");

                final TelegramRequest request = TelegramRequestFactory.createGetFileRequest(photo.getFileId());
                TelegramResponse<PhotoSize> resp = (TelegramResponse<PhotoSize>) requestHandler.sendRequest(request);
                String imgUrl = resp.getResult().get(0).getFilePath();
                LOG.info("Downloading image at path: " + imgUrl);

                String imageId = downloadAndInsertImage(imgUrl);
                if (imageId != null) {
                    currentImgId = imageId;
                }

                // Creating reply message to sender
                ForceReply keyb = new ForceReply();
                keyb.setForceReply(Boolean.TRUE);
                keyb.setSelective(Boolean.TRUE);
                TelegramRequest request2 = TelegramRequestFactory.createSendMessageRequest(message.getChat().getId(), REPLY_ALIAS, true, null, keyb);
                requestHandler.sendRequest(request2);
                return true;

            }
        }
        return found;
    }

    private int retrieveProperRowNum() {
        int size = arbitroCommands.size();
        for (int col : numColonne) {
            if (arbitroCommands.size() % col == 0) {
                return arbitroCommands.size() / col;
            }
        }
        return size;
    }

    private void updateImageAliases(String alias, String imgId) {
        LOG.info("Setting alias " + alias + " to image: " + imgId);
        final Connection conn = DBUtils.getConnection();
        try {
            final PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `arbitri` SET `alias` = ? WHERE `arbitri`.`id` = ?;");
            preparedStatement.setString(1, alias.toLowerCase());
            preparedStatement.setString(2, currentImgId);
            preparedStatement.executeUpdate();
            initAliases();
        } catch (SQLException e) {
            LOG.error("An error occurred while executing update for img: " + currentImgId);
        } finally {
            DBUtils.closeConnection(conn);
        }
    }

    private String downloadAndInsertImage(String imgUrl) {
        try {
            // Downloading the img from specified URL...
            InputStream is = new URL(FILE_DOWNLOAD_URL + imgUrl).openStream();

            final Connection conn = DBUtils.getConnection();
            try {
                final PreparedStatement preparedStatement = conn.prepareStatement(CaneConstants.ARBITRI_INSERT_QUERY);
                String imgId = "" + System.currentTimeMillis();
                preparedStatement.setString(1, imgId);
                preparedStatement.setString(2, "YET_TO_BE_FILLED");
                preparedStatement.setBinaryStream(3, is);
                preparedStatement.executeUpdate();
                return imgId;
            } catch (final SQLException e) {
                LOG.error("Something were wrong while learning new reply: " + e, e);
            } finally {
                DBUtils.closeConnection(conn);
            }

        } catch (IOException e) {
            LOG.error("An error occurred while downloading image from url: " + imgUrl + "-->" + e, e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Iterates over a configured set of aliases, and if, one of them is contained within message, replies with random image
     * 
     * @param message
     * @param requestHandler
     * @param found
     * @return
     * @throws JsonParsingException
     * @throws TelegramServerException
     */
    private boolean processSimpleTextMessage(final Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        for (String s : arbitroCommands) {
            if (message.getText().toLowerCase().contains(s)) {
                LOG.info("Searching image for alias: " + s);
                // Return arbitro from id (alias)
                final Blob b = getArbitroFromAlias(s);
                // the found blob should always be != null, as aliases are loaded when starting the program
                if (b != null) {
                    final File file = downloadFile(b);
                    if (file != null) {
                        final TelegramRequest request = TelegramRequestFactory.createSendPhotoRequest(message.getChat().getId(), file, null, null, null);
                        requestHandler.sendRequest(request);
                        file.delete();
                        return true;
                    }
                }
            }
        }

        LOG.info("Searching image for id: " + message.getText());
        final Blob b = getArbitroFromId(message.getText());
        if (b != null) {
            final File file = downloadFile(b);
            final TelegramRequest request = TelegramRequestFactory.createSendPhotoRequest(message.getChat().getId(), file, null, null, null);
            requestHandler.sendRequest(request);
            return true;
        }
        return false;
    }

    /**
     * Return true if the processing message is a reply to this command's alias question. False otherwise.
     * 
     * @param m
     *            the incoming message
     * @return true if the message's ReplyTo contains the alias phrase, false otherwise
     */
    private boolean detectReplyAliasMessage(Message m) {

        if (m.getReplyToMessage() != null && StringUtils.containsIgnoreCase(m.getReplyToMessage().getText(), REPLY_ALIAS)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Transfer the blob file from SQL to File into java.io.tmpdir to be sent out.
     * 
     * @param blob
     *            the blob to be loaded
     * @return the saved file
     */
    private File downloadFile(Blob blob) {
        OutputStream out = null;
        byte[] buff = null;
        try {
            File f = File.createTempFile("tmp", ".jpg");
            if (LOG.isDebugEnabled()) {
                LOG.info("File " + f.getName() + " created in path: " + f.getAbsolutePath());
            }
            out = new FileOutputStream(f);
            buff = blob.getBytes(1, (int) blob.length());
            out.write(buff);
            f.deleteOnExit();
            return f;
        } catch (FileNotFoundException e) {
            LOG.error("Errors creating the file, please check: " + e, e);
        } catch (SQLException e) {
            LOG.error("Errors executing the query, please check: " + e, e);
        } catch (IOException e) {
            LOG.error("I/O Error, please check: " + e, e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                LOG.error("Error while closing the stream: " + e, e);
            }
        }
        return null;

    }

    /**
     * Finds an image from the table containing the specified alias
     * 
     * @param alias
     *            the "alias" to search from
     * @return the Blob coming from the SQL table, null if the alias is not existing
     */
    public Blob getArbitroFromAlias(final String alias) {
        final Connection c = DBUtils.getConnection();
        try {
            final PreparedStatement preparedStatement = c.prepareStatement(CaneConstants.ARBITRI_SEARCH_IMAGE_FROM_ALIASES_QUERY);
            preparedStatement.setString(1, "%" + alias + "%");
            if (LOG.isDebugEnabled()) {
                LOG.debug("Query: " + preparedStatement.toString());
            }
            final ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getBlob(1);
            }
            return null;
        } catch (final SQLException ex) {
            LOG.error("There were an error retrieving the blob image: " + ex, ex);
        }
        return null;
    }

    public Blob getArbitroFromId(final String id) {
        final Connection c = DBUtils.getConnection();
        try {
            final PreparedStatement preparedStatement = c.prepareStatement(CaneConstants.ARBITRI_SEARCH_IMAGE_FROM_ID_QUERY);
            preparedStatement.setString(1, id);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Query: " + preparedStatement.toString());
            }
            final ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getBlob(1);
            }
            return null;
        } catch (final SQLException ex) {
            LOG.error("There were an error retrieving the blob image: " + ex, ex);
        } catch (final Exception ex) {
            LOG.fatal("Unmanaged error occourred: " + ex, ex);
        }
        return null;
    }

    private void initAliases() {
        List<String> aliases = new ArrayList<>();
        final Connection c = DBUtils.getConnection();
        try {
            final PreparedStatement preparedStatement = c.prepareStatement(CaneConstants.ARBITRI_ALIASES_QUERY);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Query: " + preparedStatement.toString());
            }
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String[] als = rs.getString(1).split(",");
                for (String alias : als) {
                    if (!aliases.contains(alias.toLowerCase())) {
                        aliases.add(alias.toLowerCase());
                    }
                }
            }
            Collections.sort(aliases);
            LOG.info("Loaded aliases are: " + aliases);
        } catch (final SQLException ex) {
            LOG.error("There were an error retrieving the blob image: " + ex, ex);
        }

        arbitroCommands = aliases;
    }
}

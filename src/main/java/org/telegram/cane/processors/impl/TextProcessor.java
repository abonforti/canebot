package org.telegram.cane.processors.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.telegram.cane.constants.CaneConstants;
import org.telegram.cane.core.DBUtils;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.User;

public class TextProcessor extends AbstractMessageProcessor implements MessageProcessor {

    @Override
    protected boolean isUnderAuthentication() {
        return false;
    }

    @Override
    public boolean process(Message incomingMsg, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {

        if (learnNewReply(incomingMsg)) {

            replyTextMessage("New command accepted. Grazie capo!!!", incomingMsg, requestHandler);
            return true;

        } else if (forgetReply(incomingMsg)) {
            replyTextMessage("Record entry deleted. Grazie capo!!!", incomingMsg, requestHandler);
            return true;
        } else {
            List<String> repliesFound = findTextReplies(incomingMsg);

            if (repliesFound != null && !repliesFound.isEmpty()) {

                for (String outMessage : repliesFound) {
                    if (LOG.isDebugEnabled()) {
                        LOG.info("Replying with message: " + outMessage);
                    }
                    replyTextMessage(outMessage, incomingMsg, requestHandler);
                }
                return true;

            } else if (incomingMsg.getChat() instanceof User) {
                // if no replies are found, go to default case (only in direct chat)
                String defaultMessage;
                final String usrName = extractSenderUserName(incomingMsg);
                if (StringUtils.isNotEmpty(usrName)) {
                    defaultMessage = "Cazzo vuoi, " + usrName + "?";
                } else {
                    defaultMessage = "Sei così inutile che non hai neanche un cazzo di username settato, " + incomingMsg.getFromUser().getId();
                }
                replyTextMessage(defaultMessage, incomingMsg, requestHandler);
            }
        }

        return false;
    }

    public boolean learnNewReply(Message message) {

        String msg = message.getText();
        if (StringUtils.isNotEmpty(msg) && msg.contains(CaneConstants.LEARN_COMMAND)) {
            LOG.info("New command detected: " + message);
            msg = msg.replaceFirst(CaneConstants.LEARN_COMMAND, StringUtils.EMPTY);

            String[] cmd = msg.split(CaneConstants.SPLIT_CHAR);
            if (cmd.length == 2) {
                final Connection conn = DBUtils.getConnection();
                try {
                    final PreparedStatement preparedStatement = conn.prepareStatement(CaneConstants.INSERT_QUERY);
                    preparedStatement.setString(1, cmd[0]);
                    preparedStatement.setString(2, cmd[1]);
                    preparedStatement.executeUpdate();
                    printReplies();
                    return true;
                } catch (final SQLException e) {
                    LOG.error("Something were wrong while learning new reply: " + e, e);
                } finally {
                    DBUtils.closeConnection(conn);
                }
                // printReplies();
            } else {
                throw new IllegalArgumentException("Scemo di merda, che cazzo vuoi comandare?");
            }
        }
        return false;

    }

    public boolean forgetReply(Message message) {
        String incomingMsg = message.getText();
        if (StringUtils.isNotEmpty(incomingMsg) && incomingMsg.contains(CaneConstants.DELETE_COMMAND)) {
            LOG.info("New request for deleting entry: " + incomingMsg);
            incomingMsg = incomingMsg.replaceFirst(CaneConstants.DELETE_COMMAND, StringUtils.EMPTY);
            final String[] cmd = incomingMsg.split(CaneConstants.SPLIT_CHAR);

            if (cmd.length == 2) {
                final Connection c = DBUtils.getConnection();
                try {
                    final PreparedStatement preparedStatement = c.prepareStatement(CaneConstants.DELETE_QUERY);
                    preparedStatement.setString(1, cmd[0]);
                    preparedStatement.setString(2, cmd[1]);
                    preparedStatement.executeUpdate();
                    return true;
                } catch (final SQLException sqle) {
                    LOG.error("Something were wrong when trying to delete command" + cmd[0] + " with answer " + cmd[1]);
                } finally {
                    DBUtils.closeConnection(c);
                }
            }
        }
        return false;
    }

    public List<String> findTextReplies(Message message) throws JsonParsingException, TelegramServerException {

        String incomingMsg = message.getText();

        if (StringUtils.isNotEmpty(incomingMsg)) {

            // detected outgoing words
            final ArrayList<String> messaggi = new ArrayList<>();

            // Reply "secca" (in caso di input con pi� parole)
            String singleReply = findTextReply(incomingMsg);
            if (StringUtils.isNotEmpty(singleReply)) {
                messaggi.add(singleReply);
            }

            // Splitted incoming words
            final List<String> words = Arrays.asList(incomingMsg.split(" "));
            if (words.size() > 1) {
                for (String word : words) {
                    singleReply = findTextReply(word);
                    if (StringUtils.isNotEmpty(singleReply)) {
                        if (LOG.isDebugEnabled()) {
                            LOG.info("Adding " + singleReply + " as reply for " + word);
                        }
                        messaggi.add(singleReply);
                    }
                }
            }
            return messaggi;
        }
        return Collections.emptyList();

    }

    private String findTextReply(String incomingMsg) {
        final Connection connection = DBUtils.getConnection();
        final ArrayList<String> possibleReplies = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(CaneConstants.INSULTI_QUERY);
            preparedStatement.setString(1, incomingMsg);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                possibleReplies.add(rs.getString("output"));
            }
            if (!possibleReplies.isEmpty()) {
                return extractRandomReply(possibleReplies);
            }

        } catch (final SQLException e) {
            LOG.error("Something were wrong while finding text reply: " + e, e);
        } finally {
            try {
                rs.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DBUtils.closeConnection(connection);
        }
        return null;
    }

    protected String extractRandomReply(List<String> outputs) {
        return outputs.get(new Random().nextInt(outputs.size()));
    }

    protected final void printReplies() {
        final Connection connection = DBUtils.getConnection();
        try {
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(CaneConstants.SELECT_ALL_INSULTI);
            while (rs.next()) {
                // Retrieve by column name
                LOG.info(rs.getString("input") + " -> " + rs.getString("output"));
            }
            rs.close();
            stmt.close();
        } catch (final SQLException e) {
            LOG.error("Something was wrong while printing replies: " + e, e);
        } finally {
            DBUtils.closeConnection(connection);
        }
    }

}

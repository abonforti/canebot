package org.telegram.cane.processors.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

public class MosconiProcessor extends AbstractMessageProcessor implements MessageProcessor {

    @Override
    protected boolean isUnderAuthentication() {
        return false;
    }

    /**
     * returns a randomic mosconi quote if mosconi has been detected as input msg, null otherwise
     * 
     * @param message
     * @return
     * @throws JsonParsingException
     * @throws TelegramServerException
     */
    @Override
    public boolean process(final Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {

        if (StringUtils.isNotEmpty(message.getText()) && message.getText().toLowerCase().contains("mosconi")) {
            replyTextMessage(getRandomMosconi(), message, requestHandler);
            return true;
        }
        return false;
    }

    private String getRandomMosconi() {
        final Connection c = DBUtils.getConnection();
        final ArrayList<String> mosconiReplies = new ArrayList<String>();
        try {
            final Statement stmt = c.createStatement();
            final ResultSet rs = stmt.executeQuery(CaneConstants.MOSCONI_ALL_QUERY);
            while (rs.next()) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Printing the following: " + rs.getString("quote"));
                }
                mosconiReplies.add(rs.getString("quote"));
            }
            return mosconiReplies.get(new Random().nextInt(mosconiReplies.size()));
        } catch (final SQLException ex) {
            LOG.error("Error executing mosconi query, check! " + ex, ex);
        } finally {
            DBUtils.closeConnection(c);
        }
        return StringUtils.EMPTY;
    }

}

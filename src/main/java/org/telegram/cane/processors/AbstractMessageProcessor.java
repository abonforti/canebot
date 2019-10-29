package org.telegram.cane.processors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.telegram.cane.constants.CaneConstants;
import org.telegram.cane.core.DBUtils;
import org.telegram.cane.core.PropsUtils;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.User;

public abstract class AbstractMessageProcessor implements MessageProcessor {

    protected final Logger LOG = Logger.getLogger(getClass());

    public static final String USER_GROUP_BELONGING_QUERY = "select * from users join user2groups on users.pk = user2groups.user join usergroups on user2groups.group = usergroups.pk where telegramId = ? and groupname = ?;";

    public AbstractMessageProcessor() {
        super();
    }

    @Override
    public boolean processIncomingMessage(Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        if (isUnderAuthentication()) {
            if (isAuthorized(message)) {
                return process(message, requestHandler);
            } else {
                LOG.debug("User " + extractSenderUserName(message) + " is not authorized for current processor");
            }
        } else {
            return process(message, requestHandler);
        }
        return false;
    }

    protected abstract boolean isUnderAuthentication();

    /**
     * By default, each processor is under "admingroup" authentication. If no authentication is needed, then this method
     * is overridden by returning simply true
     * 
     * @param message
     * @return true -> is authorized, false otherwise
     */
    protected boolean isAuthorized(Message message) {

        String username = message.getFromUser().getUsername();

        final Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(USER_GROUP_BELONGING_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, "admingroup");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;

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
        return false;

    }

    public abstract boolean process(final Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException;

    protected void replyTextMessage(String messaggio, final Message originalMsg, final RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        messaggio = PropsUtils.getProperty(CaneConstants.LOCAL_PREFIX, StringUtils.EMPTY) + " " + messaggio;
        final TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(originalMsg.getChat().getId(), messaggio, true, null, null);
        requestHandler.sendRequest(request);
    }

    protected String extractSenderUserName(final Message msg) {

        final User usr = msg.getFromUser();
        if (StringUtils.isNotBlank(usr.getFirstName())) {
            return usr.getFirstName();
        } else if (StringUtils.isNotBlank(usr.getLastName())) {
            return usr.getLastName();
        } else if (StringUtils.isNotBlank(usr.getUsername())) {
            return usr.getUsername();
        }

        return null;
    }

}
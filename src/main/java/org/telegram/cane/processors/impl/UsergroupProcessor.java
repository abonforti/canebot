package org.telegram.cane.processors.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.telegram.cane.constants.CaneConstants;
import org.telegram.cane.core.DBUtils;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public class UsergroupProcessor extends AbstractMessageProcessor implements MessageProcessor {

    @Override
    protected boolean isUnderAuthentication() {
        // in order to add a user you must be an admin :)
        return true;
    }

    @Override
    public boolean process(Message incomingMsg, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {

        if (addUserToGroup(incomingMsg, requestHandler)) {

            replyTextMessage("New user group record created. Grazie capo!!!", incomingMsg, requestHandler);
            return true;

        } else {
            // Nothing to do here
            return false;
        }
    }

    public boolean addUserToGroup(Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {

        String msg = message.getText();
        if (StringUtils.isNotEmpty(msg) && msg.contains(CaneConstants.ADD_USERTOGROUP)) {
            LOG.info("New user-group relation detected: " + message);
            msg = msg.replaceFirst(CaneConstants.ADD_USERTOGROUP, StringUtils.EMPTY);

            String[] cmd = msg.split(CaneConstants.SPLIT_CHAR);
            String userName = cmd[0];
            String userGroup = cmd[1];
            if (cmd.length == 2) {

                String groupPk = getGroupPk(userGroup);
                if (StringUtils.isEmpty(groupPk)) {
                    // if there is no group with that name, break: use proper command to add groups
                    replyTextMessage("Cazzo fai coglione! Non esiste un gruppo con quel nome!", message, requestHandler);
                }

                String userPk = getUserPk(userName);
                if (StringUtils.isEmpty(userPk)) {
                    // if there is no user with that name, insert user
                    final Connection conn = DBUtils.getConnection();
                    try {
                        final PreparedStatement preparedStatement = conn.prepareStatement(CaneConstants.INSERT_USER_QUERY);
                        preparedStatement.setString(1, userName);
                        preparedStatement.executeUpdate();

                        // Fetching newly created pk for further update
                        userPk = getUserPk(userName);
                    } catch (final SQLException e) {
                        LOG.error("Something were wrong while adding new user to group: " + e, e);
                    } finally {
                        DBUtils.closeConnection(conn);
                    }
                }

                // Assign user to group
                final Connection conn = DBUtils.getConnection();
                try {
                    final PreparedStatement preparedStatement = conn.prepareStatement(CaneConstants.INSERT_USER_GROUP_QUERY);
                    preparedStatement.setString(1, cmd[0]);
                    preparedStatement.setString(2, cmd[1]);
                    preparedStatement.executeUpdate();
                    return true;
                } catch (final SQLException e) {
                    LOG.error("Something were wrong while adding new user to group: " + e, e);
                } finally {
                    DBUtils.closeConnection(conn);
                }
            } else {
                throw new IllegalArgumentException("Scemo di merda, che cazzo vuoi comandare?");
            }
        }
        return false;

    }

    /**
     * Fetch the pk of a user from its telegramId
     * 
     * @param userName
     * @return
     */
    public String getUserPk(String userName) {
        final Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(CaneConstants.SELECT_PK_FROM_USER);
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getString("pk");
            }
            return null;

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

    /**
     * Fetch the pk of a group from its name
     * 
     * @param usergroup
     * @return
     */
    public String getGroupPk(String usergroup) {
        final Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(CaneConstants.SELECT_PK_FROM_USERGROUPS);
            preparedStatement.setString(1, usergroup);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getString("pk");
            }
            return null;

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

}

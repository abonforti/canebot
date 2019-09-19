package org.telegram.cane.processors;

import org.apache.commons.lang3.StringUtils;
import org.telegram.cane.constants.CaneConstants;
import org.telegram.cane.core.PropsUtils;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.User;

public abstract class AbstractMessageProcessor {

    public AbstractMessageProcessor() {
        super();
    }

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
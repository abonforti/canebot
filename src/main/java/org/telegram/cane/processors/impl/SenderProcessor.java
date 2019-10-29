package org.telegram.cane.processors.impl;

import org.apache.commons.lang3.StringUtils;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public class SenderProcessor extends AbstractMessageProcessor implements MessageProcessor {

    @Override
    protected boolean isUnderAuthentication() {
        return false;
    }

    static final String BONFORTI_USERNAME = "alestark";

    boolean enabled = false;

    @Override
    public boolean process(final Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        boolean found = false;
        if (StringUtils.isNotEmpty(message.getText()) && message.getText().contains(BONFORTI_USERNAME) && message.getText().contains("toggle")) {
            enabled = !enabled;
        }
        if (enabled && message.getFromUser() != null && message.getFromUser().getUsername() != null && message.getFromUser().getUsername().equalsIgnoreCase(BONFORTI_USERNAME)) {
            String messaggio = "SCAPPELLOTTO!!! ORCODIO!";
            final TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(message.getChat().getId(), messaggio, true, null, null);
            requestHandler.sendRequest(request);
        }
        return found;
    }

}

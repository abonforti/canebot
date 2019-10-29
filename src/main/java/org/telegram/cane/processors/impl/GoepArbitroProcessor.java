package org.telegram.cane.processors.impl;

import java.io.File;
import java.sql.Blob;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public class GoepArbitroProcessor extends ArbitroProcessor {

    @Override
    protected boolean isUnderAuthentication() {
        return true;
    }

    @Override
    public boolean process(Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {

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

}

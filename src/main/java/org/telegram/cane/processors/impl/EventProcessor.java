package org.telegram.cane.processors.impl;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public class EventProcessor extends AbstractMessageProcessor implements MessageProcessor {

    @Override
    public boolean process(Message incomingMsg, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        final String msg = findEventResponse(incomingMsg);
        if (StringUtils.isNotEmpty(msg)) {
            replyTextMessage(msg, incomingMsg, requestHandler);
            return true;
        }
        return false;
    }

    private String findEventResponse(Message message) {
        String messaggio = null;
        // Chat group created
        if (BooleanUtils.isTrue(message.getGroupChatCreated())) {
            messaggio = "We! Grazie per avermi aggiunto :D ";
        }
        // Chat has new name
        if (StringUtils.isNotEmpty(message.getNewChatTitle())) {
            messaggio = "Bella cagata,il nuovo nome del gruppo!";
        }
        // New partecipant added to chat
        if (message.getNewChatParticipantUser() != null) {
            String name = message.getNewChatParticipantUser().getFirstName() != null ? message.getNewChatParticipantUser().getFirstName()
                    : message.getNewChatParticipantUser().getUsername();
            messaggio = "Date un benvenuto a quel cane di " + name + "!";
        }
        // Partecipant removed
        if (message.getLeftChatParticipantUser() != null) {
            String name = message.getLeftChatParticipantUser().getFirstName() != null ? message.getLeftChatParticipantUser().getFirstName()
                    : message.getNewChatParticipantUser().getUsername();
            messaggio = "Finalmente l'avete levato dai maroni, quel cane di " + name + "!";
        }

        return messaggio;
    }

}

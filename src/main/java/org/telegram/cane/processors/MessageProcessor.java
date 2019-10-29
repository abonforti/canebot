package org.telegram.cane.processors;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public interface MessageProcessor {

    public boolean processIncomingMessage(Message m, final RequestHandler requestHandler) throws JsonParsingException, TelegramServerException;

}
package org.telegram.cane;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.Command;
import io.github.nixtabyte.telegram.jtelebot.server.CommandFactory;

import org.apache.log4j.Logger;

public class CaneCommandFactory implements CommandFactory {

    private static final Logger LOG = Logger.getLogger("CaneCommandFactory");

    private SimpleCaneCommand cmd;

    @Override
    public Command createCommand(Message message, RequestHandler requestHandler) {
        LOG.info("Incoming text: " + message.getText() + " id: " + message.getId() + " from: " + message.getFromUser().getFirstName() + " from chat: " + message.getChat()
                + message.getChat().getId());

        if (message.getReplyToMessage() != null) {
            LOG.info("Reply to message: " + message.getReplyToMessage().getText());
        }
        if (cmd == null) {
            cmd = new SimpleCaneCommand(message, requestHandler);
        } else {
            cmd.setMessage(message);
            cmd.setRequestHandler(requestHandler);
        }
        return cmd;
    }
}

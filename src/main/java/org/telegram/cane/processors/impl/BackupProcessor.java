package org.telegram.cane.processors.impl;

import org.apache.commons.lang3.StringUtils;
import org.telegram.cane.core.backup.DropBoxBackupper;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public class BackupProcessor extends AbstractMessageProcessor implements MessageProcessor {

    private static final String PERFORMBACKUP_COMMAND = "Canebot,performbackup";

    @Override
    protected boolean isUnderAuthentication() {
        return true;
    }

    @Override
    public boolean process(Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {

        final DropBoxBackupper dbBackupper = new DropBoxBackupper();

        String msg = message.getText();
        if (StringUtils.isNotEmpty(msg) && StringUtils.trim(msg).equalsIgnoreCase(PERFORMBACKUP_COMMAND)) {
            return dbBackupper.backupFile();
        }
        return false;
    }

}

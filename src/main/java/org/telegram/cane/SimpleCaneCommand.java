package org.telegram.cane;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.telegram.cane.core.PropsUtils;
import org.telegram.cane.core.backup.DropBoxBackupper;
import org.telegram.cane.processors.MessageProcessor;
import org.telegram.cane.processors.impl.ArbitroProcessor;
import org.telegram.cane.processors.impl.EventProcessor;
import org.telegram.cane.processors.impl.FantaProcessor;
import org.telegram.cane.processors.impl.MosconiProcessor;
import org.telegram.cane.processors.impl.SearchProcessor;
import org.telegram.cane.processors.impl.SenderProcessor;
import org.telegram.cane.processors.impl.TextProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class SimpleCaneCommand extends AbstractCommand {

    protected static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final Logger LOG = Logger.getLogger(SimpleCaneCommand.class);

    private List<MessageProcessor> processors;

    public SimpleCaneCommand(final Message message, final RequestHandler requestHandler) {
        super(message, requestHandler);

        // Loads properties from property file in resource folder
        PropsUtils.initializeProperties();

        scheduleDBBackup();

        processors = new ArrayList<>();
        EventProcessor eventProcessor = new EventProcessor();
        SearchProcessor searchProcessor = new SearchProcessor();
        MosconiProcessor mosconiProcessor = new MosconiProcessor();
        TextProcessor textProcessor = new TextProcessor();
        ArbitroProcessor arbitroProcessor = new ArbitroProcessor();
        SenderProcessor senderProcessor = new SenderProcessor();
        FantaProcessor fantaProcessor = new FantaProcessor();

        processors.add(fantaProcessor);
        processors.add(eventProcessor);
        processors.add(mosconiProcessor);
        processors.add(arbitroProcessor);
        processors.add(searchProcessor);
        processors.add(senderProcessor);
        processors.add(textProcessor);


    }

    private void scheduleDBBackup() {

        final DropBoxBackupper dbBackupper = new DropBoxBackupper();

        ScheduledExecutorService delayedExecutor = Executors.newSingleThreadScheduledExecutor();
        delayedExecutor.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                String fileName = PropsUtils.getProperty("db.backup.path");
                fileName = fileName + PropsUtils.getProperty("db.backup.filename");
                LOG.info("Starting DB backup procedure for file: " + fileName);
                File f = new File(fileName);
                if (f.exists()) {
                    dbBackupper.backupFile(f, buildBackupFileName());
                    try {
                        Files.delete(f.toPath());
                        LOG.info("File " + fileName + " deleted after successful backup");
                    } catch (final IOException ex) {
                        LOG.error("Error while deleting backup file");
                    }
                } else {
                    LOG.warn("No backup file found in path: " + fileName);
                }
            }
        }, minutesTillMidnight(), 1440, TimeUnit.MINUTES);

    }

    private String buildBackupFileName() {
        Calendar c = Calendar.getInstance();
        return "Backup_" + c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    private long minutesTillMidnight() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long millisecTillMidnight = (c.getTimeInMillis() - System.currentTimeMillis());
        return millisecTillMidnight / 1000 / 60 + 5;
    }

    @Override
    public void execute() {
        try {

            for (MessageProcessor mp : processors) {

                boolean interrupt = mp.process(message, requestHandler);
                if (interrupt)
                    break;
            }

        } catch (final JsonParsingException | TelegramServerException e) {
            LOG.error("Something were wrong while processing message: " + message + " -> " + e, e);
        }
    }

}

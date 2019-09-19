package org.telegram.cane.core.backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReadException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxFiles.Metadata;
import com.dropbox.core.v2.DbxFiles.UploadBuilder;
import com.dropbox.core.v2.DbxFiles.WriteMode;
import com.dropbox.core.v2.DbxUsers.FullAccount;

public class DropBoxBackupper {

    private final static String DROPBOX_API_KEY = System.getenv("DROPBOX_API_KEY");
    private static final Logger LOG = Logger.getLogger(DropBoxBackupper.class);

    public void backupFile(File inputFile, String targetFileName) {
        DbxRequestConfig config = new DbxRequestConfig("Canebot/1.0", Locale.getDefault().toString());
        DbxClientV2 client = new DbxClientV2(config, DROPBOX_API_KEY);
        FileInputStream inputStream = null;
        FullAccount account;
        try {
            account = client.users.getCurrentAccount();

            LOG.info("Account name: " + account.accountId + " will be used as backup...");

            List<Metadata> entries = client.files.listFolder("").entries;
            LOG.info("Files in root DropBox folder: ");
            for (Metadata metadata : entries) {
                LOG.info(metadata.name);
            }

            LOG.info("Loading file " + inputFile.getAbsolutePath() + " to target file: " + targetFileName);
            inputStream = new FileInputStream(inputFile);

            UploadBuilder builder = client.files.uploadBuilder("/" + targetFileName);
            builder.mode(WriteMode.overwrite());
            Metadata metadata = builder.run(inputStream);

            LOG.info("File: " + metadata.toString() + " uploaded correctly.");

        } catch (DbxException e) {
            LOG.error("A DbxException occurred while backing up file: " + inputFile.getAbsolutePath() + " " + e, e);
        } catch (Exception e) {
            LOG.error("A generic error occurred while backing up file: " + inputFile.getAbsolutePath() + " " + e, e);
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error("A DbxException occurred while backing up file: " + inputFile.getAbsolutePath() + " " + e, e);
                }
        }

    }

    public static void main(String[] args) throws DbxException, IOException, JsonReadException {
        // for testing purposes

        DropBoxBackupper bkp = new DropBoxBackupper();
        File f = new File("c:\\canebotdb_08022016.sql");
        bkp.backupFile(f, f.getName());

    }
}

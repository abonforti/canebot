package org.telegram.cane;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.telegram.cane.constants.CaneConstants;
import org.telegram.cane.core.PropsUtils;

import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandDispatcher;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandQueue;

public class CaneRunner {

    private static final Logger LOG = Logger.getLogger(CaneRunner.class);

    public static void main(String[] args) {

        InputStream propertyFile = null;
        try {
            propertyFile = CaneRunner.class.getClassLoader().getResource(CaneConstants.LOG4J_PROPERTIES).openStream();

            if (propertyFile != null) {
                PropertyConfigurator.configure(propertyFile);
                propertyFile.close();
            } else {
                LOG.error("No file " + CaneConstants.LOG4J_PROPERTIES + " found");
            }
        } catch (final IOException ex) {
            LOG.error("There were an exception trying to open the properties file!" + ex, ex);
        }

        PropsUtils.initializeProperties();

        DefaultCommandDispatcher disp = new DefaultCommandDispatcher(100, 10, new DefaultCommandQueue());
        disp.startUp();

        CaneCommandWatcher watcher = new CaneCommandWatcher(500, 100, CaneConstants.BOT_ID, disp, new CaneCommandFactory());
        watcher.startUp();
    }
}

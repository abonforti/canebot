package org.telegram.cane.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.telegram.cane.SimpleCaneCommand;
import org.telegram.cane.constants.CaneConstants;

/**
 * Utility class for managing different property files for the project
 * 
 * @author g.cortesi
 * 
 */
public class PropsUtils {

    private static final Logger LOG = Logger.getLogger(PropsUtils.class);

    private static Properties props;

    private static final String LOCAL_PROPERTIES_PATH = "/home/canebot/canebot/local.properties";

    private PropsUtils() {
        // do nothing
    }

    public static void initializeProperties() {
        props = new Properties();
        try {
            // Loading project properties
            loadPropertyFile(CaneConstants.PROJECT_PROPERTIES, props);
            // Loading local properties
            loadPropertyFile(CaneConstants.LOCAL_PROPERTIES, props);
        } catch (final IOException e) {
            LOG.error("Could not load properties files! Please check project configuration/classpath!" + e, e);
        }

    }

    protected static void loadPropertyFile(final String fileName, Properties props) throws IOException {
        InputStream inStream = SimpleCaneCommand.class.getClassLoader().getResourceAsStream(fileName);

        if (inStream == null) {
            LOG.warn("Couldn't load " + fileName + ", trying to rebuild it manually using " + LOCAL_PROPERTIES_PATH);
            inStream = new FileInputStream(LOCAL_PROPERTIES_PATH);
        }

        props.load(inStream);
        inStream.close();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Properties loaded correctly.");
        }
    }

    public static String getProperty(String propertyName) {
        return props.getProperty(propertyName);
    }

    public static String getProperty(String propertyName, String defaultValue) {
        String value = props.getProperty(propertyName);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }
}

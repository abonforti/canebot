package org.telegram.cane.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

import org.apache.log4j.Logger;

public class DBUtils {

    private static final Logger LOG = Logger.getLogger(DBUtils.class);

    protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    protected static Connection conn = null;

    private static Connection makeConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            final String url = MessageFormat.format(PropsUtils.getProperty("db.url"), System.getenv("DB_USERNAME"), System.getenv("DB_PASSWORD"));
            if (LOG.isDebugEnabled()) {
                LOG.debug("Connecting to db url: " + url);
            }
            return DBUtils.conn = DriverManager.getConnection(url);
        } catch (final ClassNotFoundException ex) {
            LOG.error("Cannot find the correct JDBC Driver, please check: " + ex, ex);
        } catch (final SQLException ex) {
            LOG.error("Error while executing connection to DB, please check: " + ex, ex);
        }
        return null;
    }

    public static synchronized Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = makeConnection();
            }
        } catch (SQLException e) {
            LOG.error("An error occurred while retrieving connection, " + e, e);
        }
        return conn;
    }

    public static void closeConnection(final Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (final SQLException e) {
            LOG.error("Something were wrong closing connection: " + e, e);
        }
    }

}

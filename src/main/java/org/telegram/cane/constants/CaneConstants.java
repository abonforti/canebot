package org.telegram.cane.constants;

public class CaneConstants {

    public static final String BOT_ID = System.getenv("BOT_TOKEN_ID");
    private static final String FROM = " FROM ";
    private static final String SELECT = "SELECT ";
    private static final String SELECT_STAR_FROM = "SELECT * FROM `";
    /**
     * SQL-query-related constants
     */
    public static final String INSULTI_TABLE = "insulti";
    public static final String MOSCONI_TABLE = "mosconi";
    public static final String USERS_TABLE = "users";
    public static final String GROUPS_TABLE = "usergroups";
    public static final String INSERT_QUERY = "INSERT INTO `" + INSULTI_TABLE + "` (`input`, `output`) VALUES (?, ?);";
    public static final String DELETE_QUERY = "DELETE FROM `" + INSULTI_TABLE + "` WHERE `input` = ? AND `output` = ?;";
    public static final String INSULTI_QUERY = SELECT_STAR_FROM + INSULTI_TABLE + "` WHERE LOWER(input) LIKE LOWER(?);";
    public static final String MOSCONI_ALL_QUERY = SELECT_STAR_FROM + MOSCONI_TABLE + "`;";
    public static final String SELECT_ALL_INSULTI = SELECT_STAR_FROM + INSULTI_TABLE + "`;";

    /**
     * SQL-query-related-userandgroups
     */
    public static final String SELECT_PK_FROM_USER = "SELECT PK FROM " + USERS_TABLE + " WHERE telegramId=LOWER(?)";
    public static final String SELECT_PK_FROM_USERGROUPS = "SELECT PK FROM " + GROUPS_TABLE + " WHERE groupname=LOWER(?)";
    public static final String INSERT_USER_QUERY = "INSERT INTO " + USERS_TABLE + " (telegramId) values (LOWER(?));";
    public static final String INSERT_GROUP_QUERY = "INSERT INTO " + GROUPS_TABLE + " (groupname) values (LOWER(?));";
    public static final String INSERT_USER_GROUP_QUERY = "INSERT INTO user2groups VALUES ((select pk from users where telegramId = LOWER(?)), (select pk from usergroups where groupname = LOWER(?)));";

    /**
     * SQL-query-related-arbitri constants
     */
    public static final String ARBITRI_TABLE_NAME = "arbitri";
    public static final String ARBITRI_TABLE_ID_FIELD = "id";
    public static final String ARBITRI_TABLE_ALIAS_FIELD = "alias";
    public static final String ARBITRI_TABLE_IMAGE_FIELD = "image";
    public static final String ARBITRI_ALL_QUERY = SELECT_STAR_FROM + ARBITRI_TABLE_NAME + "`;";
    public static final String ARBITRI_SEARCH_ID_QUERY = "SELECT id FROM `" + ARBITRI_TABLE_NAME + "` WHERE `" + ARBITRI_TABLE_ID_FIELD + "` LIKE ?;";
    public static final String ARBITRI_SEARCH_ALIAS_QUERY = "SELECT alias FROM `" + ARBITRI_TABLE_NAME + "` WHERE `" + ARBITRI_TABLE_ID_FIELD + "` LIKE ?;";
    public static final String ARBITRI_SEARCH_ID_FROM_ALIASES_QUERY = "SELECT id FROM arbitri WHERE " + ARBITRI_TABLE_ALIAS_FIELD + " LIKE %?%;";

    public static final String ARBITRI_SEARCH_IMAGE_FROM_ALIASES_QUERY = "SELECT image FROM arbitri WHERE `" + ARBITRI_TABLE_ALIAS_FIELD + "` LIKE ? order by RAND() limit 1;";

    public static final String ARBITRI_SEARCH_IMAGE_FROM_ID_QUERY = SELECT + ARBITRI_TABLE_IMAGE_FIELD + FROM + ARBITRI_TABLE_NAME + " WHERE LOWER(id) = LOWER(?)";

    public static final String ARBITRI_INSERT_QUERY = "INSERT INTO arbitri (id, alias, image) VALUES (?, ?, ?)";

    public static final String ARBITRI_ALIASES_QUERY = "SELECT alias FROM " + ARBITRI_TABLE_NAME + ";";

    /**
     * Command list
     */
    public static final String LEARN_COMMAND = "Canebot,impara:";
    public static final String DELETE_COMMAND = "Canebot,dimentica:";
    public static final String SEARCH_IMG_COMMAND = "Canebot,search:";
    public static final String ADD_USERTOGROUP = "Canebot,addusertogroup:";
    public static final String LIST_USERGROUPS = "Canebot,listusergroups";

    /**
     * Useful constants
     */
    public static final String SPLIT_CHAR = "->";
    public static final String PIPE = "|";

    /**
     * FileSystem-related constants
     */
    public static final String LOCAL_PREFIX = "local.prefix";
    public static final String PROJECT_PROPERTIES = "proj.properties";
    public static final String LOCAL_PROPERTIES = "local.properties";
    public static final String LOG4J_PROPERTIES = "log4j.properties";

    public static final String FANTAURL = "https://leghe.fantacalcio.it/ciampion-league?id=";
    public static final String CUP_ID = "277907";
    public static final String SERIEA_ID = "277979";
    public static final String FANTASERIEA = "fantaSerieA";
    public static final String FANTACOPPA = "fantaCoppa";

    private CaneConstants() {
        // prevent instantiating this class, 
        // should only be used as constants container
    }

}

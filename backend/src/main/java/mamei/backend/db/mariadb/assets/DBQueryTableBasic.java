package mamei.backend.db.mariadb.assets;

public class DBQueryTableBasic {
    public static final String showDatabases = "SHOW DATABASES";
    public static final String showAllUsers = "SELECT user FROM mysql.user";
    public static final String showPrivilegesForUser_Local_1 = "SHOW GRANTS FOR '";
    public static final String showPrivilegesForUser_Local_2 = "'@'localhost'";
    public static final String showPrivilegesForUser_Global_1 = "SHOW GRANTS FOR '";
    public static final String showPrivilegesForUser_Global_2 = "'@'%'";
    public static final String describe = "DESCRIBE ";
    public static final String createDatabase= "CREATE DATABASE";
    public static final String showTables="SHOW TABLES";
    public static final String dropTable="DROP TABLE ";
}

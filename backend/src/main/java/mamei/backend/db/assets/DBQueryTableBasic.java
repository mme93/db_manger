package mamei.backend.db.assets;

public class DBQueryTableBasic {
    public static final String showDatabases = "SHOW DATABASES";
    public static final String showAllUsers = "SELECT user FROM mysql.user";
    public static final String showPrivilegesForUser_1 = "SHOW GRANTS FOR '";
    public static final String showPrivilegesForUser_2 = "'@'localhost'";
}

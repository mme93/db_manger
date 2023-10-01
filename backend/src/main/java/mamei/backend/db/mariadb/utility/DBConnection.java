package mamei.backend.db.mariadb.utility;

import mamei.backend.db.mariadb.assets.DBSettingsConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBConnection {

    @Value("${pi_maria_db.ip}")
    private String pi_maria_db_ip;

    @Value("${pi_maria_db.user}")
    private String pi_maria_db_userName;

    @Value("${pi_maria_db.password}")
    private String pi_maria_db_password;

    @Value("${cloud_server_maria_db.ip}")
    private String cloud_server_maria_db_ip;

    @Value("${cloud_server_maria_db.user}")
    private String cloud_server_maria_db_userName;

    @Value("${cloud_server_maria_db.password}")
    private String cloud_server_maria_db_password;

    @Value("${cloud_xxl_maria_db.ip}")
    private String cloud_xxl_maria_db_ip;

    @Value("${cloud_xxl_maria_db.user}")
    private String cloud_xxl_maria_db_userName;

    @Value("${cloud_xxl_maria_db.password}")
    private String cloud_xxl_maria_db_password;

    private String database = "";
    private boolean isDatabaseConnection = false;

    public Connection createConnection(String serverName) throws SQLException {
        List<String> configList = getConfiguration(serverName);
        if(configList.isEmpty()){
            throw new SQLException("No Configuration found for Servername: "+serverName);
        }
        if (isDatabaseConnection && database.length() > 0) {
            return DriverManager.getConnection(configList.get(0) + database, configList.get(1), configList.get(2));
        } else{
            return DriverManager.getConnection(configList.get(0), configList.get(1), configList.get(2));
        }
        //throw new SQLException("Error no Database with Name "+serverName+" found!");
    }

    public List<String> getConfiguration(String serverName) {
        if (DBSettingsConstants.PI.equals(serverName)) {
            return new ArrayList<>(Arrays.asList(
                    pi_maria_db_ip,
                    pi_maria_db_userName,
                    pi_maria_db_password)
            );
        } else if (DBSettingsConstants.CLOUD_SERVER.equals(serverName)) {
            return new ArrayList<>(Arrays.asList(
                    cloud_server_maria_db_ip,
                    cloud_server_maria_db_userName,
                    cloud_server_maria_db_password)
            );
        } else if (DBSettingsConstants.CLOUD_XXL.equals(serverName)) {
            return new ArrayList<>(Arrays.asList(
                    cloud_xxl_maria_db_ip,
                    cloud_xxl_maria_db_userName,
                    cloud_xxl_maria_db_password)
            );
        }
        return new ArrayList<>();
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public boolean isDatabaseConnection() {
        return isDatabaseConnection;
    }

    public void setDatabaseConnection(boolean databaseConnection) {
        isDatabaseConnection = databaseConnection;
    }
}

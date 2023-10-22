package mamei.backend.datenbank.mariadb.db.service;

import mamei.backend.datenbank.mariadb.db.constants.ServerConst;
import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.db.util.Check;
import mamei.backend.db.mariadb.config.assets.DBSettingsConstants;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DatabaseConnectionService {

    public Connection initConnection(DatabaseServer server, boolean onlyServer) throws SQLException {
        if (Check.isEmptyOrNull(server.getDatabaseName()) || Check.isEmptyOrNull(server.getDatabaseName()) || Check.isEmptyOrNull(server.getServerName())) {
            throw new SQLException("No validate data");
        }
        if (onlyServer) {
            return generateServerConnection(server.getServerName());
        } else {
            return generateDatabaseConnection(server.getServerName(),server.getDatabaseName());
        }
    }

    private Connection generateServerConnection(String serverName) throws SQLException {
        List<String> configList = getConfiguration(serverName);
        if (configList.isEmpty()) {
            throw new SQLException("No Configuration found for Servername: " + serverName);
        }
        return DriverManager.getConnection(configList.get(0), configList.get(1), configList.get(2));
    }

    private Connection generateDatabaseConnection(String serverName, String databaseName) throws SQLException {
        List<String> configList = getConfiguration(serverName);
        if (configList.isEmpty()) {
            throw new SQLException("No Configuration found for Servername: " + serverName);
        }
        return DriverManager.getConnection(configList.get(0) + databaseName, configList.get(1), configList.get(2));
    }

    private List<String> getConfiguration(String serverName) {
        if (DBSettingsConstants.PI.equals(serverName)) {
            return new ArrayList<>(Arrays.asList(
                    ServerConst.pi_maria_db_ip,
                    ServerConst.pi_maria_db_userName,
                    ServerConst.pi_maria_db_password)
            );
        } else if (DBSettingsConstants.CLOUD_SERVER.equals(serverName)) {
            return new ArrayList<>(Arrays.asList(
                    ServerConst.cloud_server_maria_db_ip,
                    ServerConst.cloud_server_maria_db_userName,
                    ServerConst.cloud_server_maria_db_password)
            );
        } else if (DBSettingsConstants.CLOUD_XXL.equals(serverName)) {
            return new ArrayList<>(Arrays.asList(
                    ServerConst.cloud_xxl_maria_db_ip,
                    ServerConst.cloud_xxl_maria_db_userName,
                    ServerConst.cloud_xxl_maria_db_password)
            );
        }
        return new ArrayList<>();
    }

}

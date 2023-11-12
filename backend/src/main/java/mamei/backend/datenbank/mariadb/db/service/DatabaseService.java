package mamei.backend.datenbank.mariadb.db.service;

import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.db.util.sql.SQLStatement;
import mamei.backend.datenbank.mariadb.db.util.sqlgenerator.ServerQueryGenerator;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class DatabaseService {

    private final ServerQueryGenerator serverQueryGenerator;
    private final DatabaseConnectionService connectionService;
    private final SQLStatement sqlStatement;

    public DatabaseService(ServerQueryGenerator serverQueryGenerator, DatabaseConnectionService connectionService, SQLStatement sqlStatement) {
        this.serverQueryGenerator = serverQueryGenerator;
        this.connectionService = connectionService;
        this.sqlStatement = sqlStatement;
    }

    public List<String> getDatabaseNameByServer(String serverName) throws SQLException {
        String response = sqlStatement.executeStatement(serverQueryGenerator.showAllDatabaseFromServer(), connectionService.createConnection(serverName), 1);
        List<String> databaseNameList = asList(response.split("\n"));
        return databaseNameList;
    }

    public boolean deleteDatabase(DatabaseServer databaseServer) throws SQLException {
        String response = sqlStatement.executeUpdateStatement(
                serverQueryGenerator.deleteDatabaseQuery(databaseServer.getDatabaseName()),
                connectionService.createConnection(databaseServer.getServerName()));
        System.err.println(response);
        return true;
    }

    public boolean createDatabase(DatabaseServer databaseServer) throws SQLException {
        String response = sqlStatement.executeUpdateStatement(
                serverQueryGenerator.createDatabaseQuery(databaseServer.getDatabaseName()),
                connectionService.createConnection(databaseServer.getServerName()));
        System.err.println(response);
        return true;
    }
}

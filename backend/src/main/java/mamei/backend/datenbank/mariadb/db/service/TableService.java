package mamei.backend.datenbank.mariadb.db.service;

import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.db.util.sqlgenerator.TableQueryGenerator;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    private final DatabaseConnectionService connectionService;
    private final TableQueryGenerator tableQueryGenerator;

    public TableService(DatabaseConnectionService connectionService, TableQueryGenerator tableQueryGenerator) {
        this.connectionService = connectionService;
        this.tableQueryGenerator = tableQueryGenerator;
    }

    public List<String> getTableNamesFromDatabase(DatabaseServer databaseServer) throws SQLException {
        List<String>tableNames= new ArrayList<>();
        Connection connection = this.connectionService.createConnection(databaseServer.getServerName());
        String query = tableQueryGenerator.generateQueryAllTableNamesFromDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String tableName = resultSet.getString(1); // Der Index 1 entspricht dem Tabellennamen
            System.out.println("Table Name: " + tableName);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return tableNames;
    }

    public boolean existTable(DatabaseServer databaseServer) throws SQLException {
        Connection connection = this.connectionService.createConnection(databaseServer.getServerName());
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?")) {
            preparedStatement.setString(1, databaseServer.getDatabaseName());
            preparedStatement.setString(2, databaseServer.getTableName());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}

package mamei.backend.datenbank.mariadb.html.service;

import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.db.service.DatabaseConnectionService;
import mamei.backend.db.mariadb.table.model.create.CTableObject;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TableService {

    private final DatabaseConnectionService connectionService;

    public TableService(DatabaseConnectionService connectionService) {
        this.connectionService = connectionService;
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

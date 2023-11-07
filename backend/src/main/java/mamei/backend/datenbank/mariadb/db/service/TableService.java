package mamei.backend.datenbank.mariadb.db.service;

import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.db.model.report.TableCreateReport;
import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;
import mamei.backend.datenbank.mariadb.db.model.table.TableCreate;
import mamei.backend.datenbank.mariadb.db.model.table.TableObject;
import mamei.backend.datenbank.mariadb.db.model.table.TableView;
import mamei.backend.datenbank.mariadb.db.util.reportgenerator.CreateTableReportGenerator;
import mamei.backend.datenbank.mariadb.db.util.sqlgenerator.TableQueryGenerator;
import mamei.backend.datenbank.mariadb.db.util.validator.TableValidator;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    private final DatabaseConnectionService connectionService;
    private final TableQueryGenerator tableQueryGenerator;
    private final TableValidator tableValidator;
    private final CreateTableReportGenerator createTableReportGenerator;

    public TableService(DatabaseConnectionService connectionService, TableQueryGenerator tableQueryGenerator, TableValidator tableValidator, CreateTableReportGenerator createTableReportGenerator) {
        this.connectionService = connectionService;
        this.tableQueryGenerator = tableQueryGenerator;
        this.tableValidator = tableValidator;
        this.createTableReportGenerator = createTableReportGenerator;
    }

    public TableView getTableContext(DatabaseServer databaseServer) throws SQLException {
        Connection connection = this.connectionService.createConnection(databaseServer.getServerName(), databaseServer.getDatabaseName());
        TableObject tableObject = new TableObject()
                .builder()
                .whitDatabaseServer(databaseServer)
                .whitConnection(connection)
                .loadTableHeaderContext()
                .loadTableMetaContext()
                .withTableSize()
                .closeConnection();
        TableView tableView = new TableView();
        tableView.setTableName(tableObject.getDatabaseServer().getTableName());
        tableView.setTableSize(tableObject.getTableSize());
        tableView.setTableColumns(tableObject.getTableColumns());
        tableView.setTableMetaRows(tableObject.getTableMetaRows());
        return tableView;
    }

    public List<String> getTableNamesFromDatabase(DatabaseServer databaseServer) throws SQLException {
        List<String> tableNames = new ArrayList<>();
        Connection connection = this.connectionService.createConnection(databaseServer.getServerName(), databaseServer.getDatabaseName());
        String query = tableQueryGenerator.generateQueryAllTableNamesFromDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String tableName = resultSet.getString(1);
            tableNames.add(tableName);
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

    public TableCreateReport validateCreatTable(TableCreate tableCreate) throws SQLException {
        return tableValidator.isCreateTableValid(tableCreate);
    }

    public boolean createTable(TableCreate tableCreate) {
        try {
            Connection connection = this.connectionService.createConnection(tableCreate.getDatabaseServer().getServerName(),tableCreate.getDatabaseServer().getDatabaseName());
            String query=tableQueryGenerator.generateQueryCreateTable(tableCreate);
            Statement statement = connection.createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

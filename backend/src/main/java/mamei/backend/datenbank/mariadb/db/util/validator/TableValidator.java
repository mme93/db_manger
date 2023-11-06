package mamei.backend.datenbank.mariadb.db.util.validator;

import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.db.model.report.TableCreateReport;
import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;
import mamei.backend.datenbank.mariadb.db.model.table.TableCreate;
import mamei.backend.datenbank.mariadb.db.service.DatabaseConnectionService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableValidator {

    private final DatabaseConnectionService connectionService;

    public TableValidator(DatabaseConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public TableCreateReport isCreateTableValid(TableCreate tableCreate) throws SQLException {
        TableCreateReport report = new TableCreateReport();
        report.setTableName(tableCreate.getDatabaseServer().getTableName());
        report = checkTableName(report,tableCreate.getDatabaseServer());
        report = hasPrimaryKey(report, tableCreate.getTableMetaColumnList());
        return report;
    }

    private TableCreateReport checkTableName(TableCreateReport report, DatabaseServer databaseServer) throws SQLException {
        String tableNameReport = "";
        report.setTableNameValid(true);
        if (tableNameAllReadyExist(report.getTableName(),databaseServer)) {
            tableNameReport = "Table all ready exist. \n";
            report.setTableNameValid(false);
        }else if (!checkTableNamRules(report.getTableName())) {
            tableNameReport = generateTableNameImprovement(tableNameReport, report.getTableName());
            report.setTableNameValid(false);
        }

        report.setTableNameReport(tableNameReport);
        return report;
    }

    private String generateTableNameImprovement(String tableNameReport, String tableName) {
        if (tableName.length() > 0) {
            tableNameReport = tableNameReport + "Problems with Table name Convention, here is one improvement exampel: \n";
            tableNameReport = tableNameReport + "New Table Name: " + tableName.replaceAll(" ", "_").toLowerCase();
        } else {
            tableNameReport = tableNameReport + "Table Name ist empty.";
        }
        return tableNameReport;
    }

    private boolean checkTableNamRules(String tableName) {
        if(tableName.contains(" ")){
            return false;
        }
        List<Character> charList = new ArrayList<>();
        if (tableName.length() > 0) {
            for (char c : tableName.toCharArray()) {
                charList.add(c);
            }
            return (charList.stream().filter(c -> Character.isUpperCase(c)||c.equals("_")).count() == 0);
        }
        return false;
    }

    private boolean tableNameAllReadyExist(String tableName, DatabaseServer databaseServer) throws SQLException {
        if (tableName == null || tableName.length() == 0) {
            return false;
        }

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

    private TableCreateReport hasPrimaryKey(TableCreateReport report, List<TableColumn> tableMetaColumnList) {
        long count = tableMetaColumnList.stream().filter(tableColumn -> {
            if (tableColumn.getColumnKey() != null && tableColumn.getColumnKey().equals("PRIMARY")) {
                return true;
            }
            return false;
        }).count();
        report.setKeyValid(false);
        if (count == 0) {
            report.setKeyReport("No Key Primary Key found.");
            return report;
        } else if (count > 1) {
            report.setKeyReport("More than one Primary key found.");
            return report;
        }
        report.setKeyValid(true);
        return report;
    }

    private boolean checkAutoIncrementRules() {

        return true;
    }

    private boolean checkColNameRules() {

        return true;
    }

}

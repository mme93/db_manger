package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.model.table.TableObject;
import mamei.backend.db.mariadb.model.table.VTableObject;
import mamei.backend.db.mariadb.utility.DBConnection;
import mamei.backend.db.mariadb.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class TableService {

    private final DBSettingsUtility dbSettingsUtility;
    private final DBConnection connection;

    public TableService(DBSettingsUtility dbSettingsUtility, DBConnection connection) {
        this.dbSettingsUtility = dbSettingsUtility;
        this.connection = connection;
    }

    public List<String> getAllTablesFromAllDatabases() throws SQLException {
        return null;
    }

    public List<String> getAllTablesFromDatabase(String database) {
        return null;
    }

    public void getTableFromDatabase(String database, String table) {

    }

    public void createTable(String database, String table) {

    }

    public void dropTable(String database, String table) {

    }

    public void getTableInformation(String database, String table) {

    }

    public void addDataToTable() {

    }

    public void removeDataFromTable() {

    }

    /*
    TODO: Die richige Connection auswählen, nicht das die falsche auf Grund des Server genommen wird
     */
    public VTableObject getTableContext(String tableName, String databaseName, String serverName) throws SQLException {
        TableObject tableObject= new TableObject(tableName,databaseName,serverName);
        tableObject.initTable(connection.createConnection(serverName));
        tableObject.loadTableContext(connection.createConnection(serverName));
        return new VTableObject(
                tableObject.getTableName(),
                tableObject.getDatabaseName(),
                tableObject.getServerName(),
                tableObject.getColumnList()
        );
    }

    /**
     *
     * @param databaseName
     * @param tableName
     * @param serverName
     * @return
     * @throws SQLException
     */
    public boolean checkTableExist(String databaseName,String tableName,String serverName) throws SQLException {
        Connection connection= this.connection.createConnection(serverName);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?")) {
            preparedStatement.setString(1, databaseName);
            preparedStatement.setString(2, tableName);

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

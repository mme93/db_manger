package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.assets.DBSettingsConstants;
import mamei.backend.db.mariadb.model.table.TableObject;
import mamei.backend.db.mariadb.utility.DBConnection;
import mamei.backend.db.mariadb.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
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

    public void test() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://217.160.26.246:3306/", "remote_user","!xyz123456");
        String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = " +
                "'mameie' AND TABLE_NAME = 'db_manager'";
        StringBuilder stringBuilder = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
               // stringBuilder.append(resultSet.getString(1) + "\n");
                System.err.println(resultSet.getString(1));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.close();
    }

    /*
    TODO: Die richige Connection auswählen, nicht das die falsche auf Grund des Server genommen wird
     */
    public TableObject getTableContext(String tableName, String databaseName, String serverName) throws SQLException {
        TableObject tableObject= new TableObject(tableName,databaseName,serverName);
        tableObject.initTable(connection.createConnection(serverName));
        tableObject.loadTableContext(connection.createConnection(serverName));
        return tableObject;
    }

}

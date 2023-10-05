package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.model.table.CTableObject;
import mamei.backend.db.mariadb.model.table.ColumnMetaObject;
import mamei.backend.db.mariadb.model.table.TableObject;
import mamei.backend.db.mariadb.model.table.VTableObject;
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

    public List<String> getTableFromDatabase(String database) {
        List<String> tableNameList= new ArrayList<>();

        return tableNameList;
    }

    public String createTable(CTableObject tableObject) throws SQLException {
        String query = createTableQuery(tableObject.getColumnList(), tableObject.getTableName());
        return dbSettingsUtility.preparedStatement(query, connection.createDatabaseConnection(tableObject.getServerName(), tableObject.getDatabaseName()));
    }

    public void dropTable(String database, String table) {

    }

    public void getTableInformation(String database, String table) {

    }

    public void addDataToTable() {

    }

    public void removeDataFromTable() {

    }


    public VTableObject getTableContext(String tableName, String databaseName, String serverName) throws SQLException {
        TableObject tableObject = new TableObject(tableName, databaseName, serverName);
        tableObject.initTable(connection.createConnection(serverName));
        return new VTableObject(
                tableObject.getTableName(),
                tableObject.getDatabaseName(),
                tableObject.getServerName(),
                tableObject.getColumnMetaObjectList(),
                tableObject.loadTableContext(connection.createDatabaseConnection(serverName, databaseName))
        );
    }

    /**
     * @param databaseName
     * @param tableName
     * @param serverName
     * @return
     * @throws SQLException
     */
    public boolean checkTableExist(String databaseName, String tableName, String serverName) throws SQLException {
        Connection connection = this.connection.createConnection(serverName);
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

    public String createTableQuery(List<ColumnMetaObject> columnList, String tableName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE " + tableName + " ( ");
        for (int i = 0; i < columnList.size(); i++) {
            if (i != columnList.size() - 1) {
                stringBuilder.append(createRowQuery(columnList.get(i)) + ", \n");
            } else {
                stringBuilder.append(createRowQuery(columnList.get(i)) + " )");
            }
        }
        return stringBuilder.toString();
    }

    public String createRowQuery(ColumnMetaObject columnMetaObject) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(columnMetaObject.getColumnName() + " ");
        if (columnMetaObject.getMySQLDataTypeEnum() != null) {
            stringBuilder.append(columnMetaObject.getMySQLDataTypeEnum() + " ");
        }
        if (columnMetaObject.isIS_NULLABLE()) {
            stringBuilder.append("NULL");
        } else if (!columnMetaObject.isIS_NULLABLE()) {
            stringBuilder.append("NOT NULL");
        }
        if (columnMetaObject.getCOLUMN_KEY() != null) {
            stringBuilder.append(columnMetaObject.getCOLUMN_KEY() + " ");
        }
        if (columnMetaObject.getCOLUMN_DEFAULT() != null) {
            stringBuilder.append(columnMetaObject.getCOLUMN_DEFAULT() + " ");
        }
        if (columnMetaObject.getEXTRA() != null) {
            stringBuilder.append(columnMetaObject.getEXTRA() + " ");
        }
        return stringBuilder.toString();
    }

}

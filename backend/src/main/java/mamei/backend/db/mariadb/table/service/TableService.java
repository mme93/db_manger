package mamei.backend.db.mariadb.table.service;

import mamei.backend.db.mariadb.config.assets.DBQueryTableBasic;
import mamei.backend.db.mariadb.config.model.DBServer;
import mamei.backend.db.mariadb.table.model.object.*;
import mamei.backend.db.mariadb.table.model.view.VTableObject;
import mamei.backend.db.mariadb.config.utils.DBConnection;
import mamei.backend.db.mariadb.config.utils.DBSettingsUtility;
import mamei.backend.db.mariadb.table.utils.TableQueryGenerator;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class TableService {

    private final TableQueryGenerator tableQueryGenerator;
    private final DBSettingsUtility dbSettingsUtility;
    private final DBConnection connection;

    public TableService(TableQueryGenerator tableQueryGenerator, DBSettingsUtility dbSettingsUtility, DBConnection connection) {
        this.tableQueryGenerator = tableQueryGenerator;
        this.dbSettingsUtility = dbSettingsUtility;
        this.connection = connection;
    }


    public List<VTableObject> getAllTablesFromDatabase(String databaseName, String serverName) throws SQLException {
        String result = dbSettingsUtility.preparedStatementWithOneParameter(DBQueryTableBasic.showTables, connection.createDatabaseConnection(serverName, databaseName), 1);
        List<VTableObject> tableObjectList = new ArrayList<>();
        for (String tableName : result.split("\n")) {
            tableObjectList.add(getTableContext(new CTableObject(tableName, databaseName, serverName, asList())));
        }
        return tableObjectList;
    }

    public void createTable(CTableObject tableObject) throws SQLException {
        String query = tableQueryGenerator.createTableQuery(tableObject.getColumnList(), tableObject.getTableName());
        dbSettingsUtility.onlyExcuteQuery(query, connection.createDatabaseConnection(tableObject.getServerName(), tableObject.getDatabaseName()));
    }

    public void dropTable(DBServer dbServer) throws SQLException {
        String query = DBQueryTableBasic.dropTable + dbServer.getTableName();
        dbSettingsUtility.onlyExcuteQuery(query, connection.createDatabaseConnection(dbServer.getServerName(), dbServer.getDatabaseName()));
    }

    public void updateDataSet(TableDataSetObj tableDataSetObj) throws SQLException {
        for (List<TableColumnDataInfo> tableColumnDataInfoList : tableDataSetObj.getTableColumnDataInfoList()) {
            String query = tableQueryGenerator.createUpdateQuery(tableColumnDataInfoList,tableDataSetObj.getDbServer().getTableName());
            dbSettingsUtility.onlyExcuteQuery(query, connection.createDatabaseConnection(tableDataSetObj.getDbServer().getServerName(), tableDataSetObj.getDbServer().getDatabaseName()));
        }
    }

    public void addDataToTable(String serverName, String database, String tableName, List<TableColumnDataInfo> tableDataSetObjs) throws SQLException {
        String query = tableQueryGenerator.createInsertQuery(tableName, tableDataSetObjs);
        dbSettingsUtility.onlyExcuteQuery(query, connection.createDatabaseConnection(serverName, database));
    }

    public void removeDataSetsFromTable(DBServer dbServer, List<String> iDs) throws SQLException {
        for (String id : iDs) {
            String query = "DELETE FROM " + dbServer.getTableName() + " WHERE id =" + id;
            dbSettingsUtility.onlyExcuteQuery(query, connection.createDatabaseConnection(dbServer.getServerName(), dbServer.getDatabaseName()));
        }
    }

    public void removeDataSetFromTable(DBServer dbServer, String id) throws SQLException {
        String query = "DELETE FROM " + dbServer.getTableName() + " WHERE id =" + id;
        dbSettingsUtility.onlyExcuteQuery(query, connection.createDatabaseConnection(dbServer.getServerName(), dbServer.getDatabaseName()));
    }


    public VTableObject getTableContext(CTableObject cTableObject) throws SQLException {
        TableObject tableObject = new TableObject(cTableObject.getTableName(), cTableObject.getDatabaseName(), cTableObject.getServerName());
        tableObject.initTable(connection.createConnection(cTableObject.getServerName()));
        return new VTableObject(
                tableObject.getTableName(),
                tableObject.getDatabaseName(),
                tableObject.getServerName(),
                tableObject.getColumnMetaObjectList(),
                tableObject.loadTableContext(connection.createDatabaseConnection(cTableObject.getServerName(), cTableObject.getDatabaseName()))
        );
    }

    /**
     * @param tableObject
     * @return
     * @throws SQLException
     */
    public boolean checkTableExist(CTableObject tableObject) throws SQLException {
        Connection connection = this.connection.createConnection(tableObject.getServerName());
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?")) {
            preparedStatement.setString(1, tableObject.getDatabaseName());
            preparedStatement.setString(2, tableObject.getTableName());

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

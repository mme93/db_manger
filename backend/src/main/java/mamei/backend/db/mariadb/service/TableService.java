package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.assets.DBQueryTableBasic;
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
import java.util.Locale;

import static java.util.Arrays.asList;

@Service
public class TableService {

    private final DBSettingsUtility dbSettingsUtility;
    private final DBConnection connection;

    public TableService(DBSettingsUtility dbSettingsUtility, DBConnection connection) {
        this.dbSettingsUtility = dbSettingsUtility;
        this.connection = connection;
    }


    public List<VTableObject> getAllTablesFromDatabase(String databaseName,String serverName) throws SQLException {
        String result=dbSettingsUtility.preparedStatementWithOneParameter(DBQueryTableBasic.showTables,connection.createDatabaseConnection(serverName,databaseName),1);
        List<VTableObject>tableObjectList= new ArrayList<>();
        for(String tableName:result.split("\n")){
           tableObjectList.add(getTableContext(new CTableObject(tableName,databaseName,serverName,asList())));
        }
       return tableObjectList;
    }

    public void createTable(CTableObject tableObject) throws SQLException {
        String query = createTableQuery(tableObject.getColumnList(), tableObject.getTableName());
        dbSettingsUtility.onlyExcuteQuery(query, connection.createDatabaseConnection(tableObject.getServerName(), tableObject.getDatabaseName()));
    }

    public void dropTable(String database, String table,String serverName) throws SQLException {
        String query = DBQueryTableBasic.dropTable+table;
        dbSettingsUtility.onlyExcuteQuery(query, connection.createDatabaseConnection(serverName, database));
    }

    public void getTableInformation(String database, String table) {

    }

    public void addDataToTable() {

    }

    public void removeDataFromTable() {

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

    public String createTableQuery(List<ColumnMetaObject> columnList, String tableName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE " + tableName + " ( ");
        for (int i = 0; i < columnList.size(); i++) {
            if (i != columnList.size() - 1) {
                stringBuilder.append(createRowQuery(columnList.get(i)) + ", \n");
                if (columnList.get(i).getCOLUMN_KEY() != null && !columnList.get(i).getCOLUMN_KEY().toLowerCase(Locale.ROOT).contains("null")) {
                    stringBuilder.append("PRIMARY KEY ("+columnList.get(i).getColumnName() + "), \n");
                }
            } else {
                stringBuilder.append(createRowQuery(columnList.get(i)) + "\n )");
                if (columnList.get(i).getCOLUMN_KEY() != null && !columnList.get(i).getCOLUMN_KEY().toLowerCase(Locale.ROOT).contains("null")) {
                    stringBuilder.append("PRIMARY KEY ("+columnList.get(i).getColumnName() + ")\n )");
                }
            }
        }
        return stringBuilder.toString();
    }

    public String createRowQuery(ColumnMetaObject columnMetaObject) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(columnMetaObject.getColumnName() + " ");
       // if (columnMetaObject.getMySQLDataTypeEnum() != null) {
      //      stringBuilder.append(columnMetaObject.getMySQLDataTypeEnum() + " ");
      //  }
        if (columnMetaObject.getCOLUMN_TYPE()!=null && !columnMetaObject.getCOLUMN_TYPE().toLowerCase(Locale.ROOT).contains("null")) {
            stringBuilder.append(columnMetaObject.getCOLUMN_TYPE() + " ");
        }
        if (columnMetaObject.isIS_NULLABLE()) {
            stringBuilder.append("NULL ");
        } else if (!columnMetaObject.isIS_NULLABLE()) {
            stringBuilder.append("NOT NULL ");
        }
        if (columnMetaObject.getCOLUMN_DEFAULT() != null && !columnMetaObject.getCOLUMN_DEFAULT().toLowerCase(Locale.ROOT).contains("null")) {
            stringBuilder.append(columnMetaObject.getCOLUMN_DEFAULT() + " ");
        }
        if (columnMetaObject.getEXTRA() != null && !columnMetaObject.getEXTRA().toLowerCase(Locale.ROOT).contains("null")) {
            stringBuilder.append(columnMetaObject.getEXTRA() + " ");
        }
        return stringBuilder.toString();
    }

}

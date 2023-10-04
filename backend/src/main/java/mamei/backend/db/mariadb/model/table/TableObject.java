package mamei.backend.db.mariadb.model.table;

import lombok.Getter;
import lombok.Setter;
import mamei.backend.db.mariadb.model.ENUM.MySQLDataType;
import mamei.backend.db.mariadb.utility.SQLEnumConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
public class TableObject {

    private String tableName;

    private String databaseName;

    private String serverName;

    private List<List<ColumnDataObject>> columnRowList;

    private List<ColumnMetaObject> columnMetaObjectList;

    public TableObject(String tableName, String databaseName, String serverName) {
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.serverName = serverName;
    }

    public void initTable(Connection connection) throws SQLException {
        columnMetaObjectList = getColumnNameList(connection, createColumnNamesQuery());
        columnMetaObjectList = loadColumnProperties(connection, createColumnPropertiesQuery());
        connection.close();
    }

    public List<ColumnRowObject>  loadTableContext(Connection connection) throws SQLException {
        List<ColumnRowObject> columnRows = new ArrayList<>();
        String query = createTableDataQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int index = 0;
            while (resultSet.next()) {
                List<ColumnDataObject> columnRow = new ArrayList<>();
                for (ColumnMetaObject columnMetaObject : columnMetaObjectList) {
                    if(columnMetaObject.getMySQLDataTypeEnum().equals(MySQLDataType.INT)){
                        columnRow.add(new ColumnDataObject(
                                columnMetaObject.getColumnName(),
                                resultSet.getInt(columnMetaObject.getColumnName())
                        ));
                    }else if(columnMetaObject.getMySQLDataTypeEnum().equals(MySQLDataType.VARCHAR)){
                        columnRow.add(new ColumnDataObject(
                                columnMetaObject.getColumnName(),
                                resultSet.getString(columnMetaObject.getColumnName())
                        ));
                    }
                }
                columnRows.add(new ColumnRowObject(columnRow,index));
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.close();
        return columnRows;
    }

    private List<ColumnMetaObject> loadColumnProperties(Connection connection, String query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int index = 0;
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    if (resultSet.getString(i) == null || resultSet.getString(i).isEmpty()) {
                        if (i == 1) {
                            columnMetaObjectList.get(index).setColumnName("EMPTY");
                        } else if (i == 2) {
                            columnMetaObjectList.get(index).setCOLUMN_TYPE("EMPTY");
                            columnMetaObjectList.get(index).setMySQLDataTypeEnum(null);
                        } else if (i == 3) {
                            columnMetaObjectList.get(index).setIS_NULLABLE(false);
                        } else if (i == 4) {
                            columnMetaObjectList.get(index).setCOLUMN_KEY("EMPTY");
                        } else if (i == 5) {
                            columnMetaObjectList.get(index).setCOLUMN_DEFAULT("EMPTY");
                        } else if (i == 6) {
                            columnMetaObjectList.get(index).setEXTRA("EMPTY");
                        }
                    } else {
                        if (i == 1) {
                            columnMetaObjectList.get(index).setColumnName(resultSet.getString(i));
                        } else if (i == 2) {
                            columnMetaObjectList.get(index).setCOLUMN_TYPE(resultSet.getString(i));
                            columnMetaObjectList.get(index).setMySQLDataTypeEnum(
                                    SQLEnumConverter.getEnumFROMSQLTypString(resultSet.getString(i))
                            );
                        } else if (i == 3) {
                            columnMetaObjectList.get(index).setIS_NULLABLE(resultSet.getString(i).equals("NO"));
                        } else if (i == 4) {
                            columnMetaObjectList.get(index).setCOLUMN_KEY(resultSet.getString(i));
                        } else if (i == 5) {
                            columnMetaObjectList.get(index).setCOLUMN_DEFAULT(resultSet.getString(i));
                        } else if (i == 6) {
                            columnMetaObjectList.get(index).setEXTRA(resultSet.getString(i));
                        }
                    }

                }
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnMetaObjectList;
    }


    private List<ColumnMetaObject> getColumnNameList(Connection connection, String query) {
        columnMetaObjectList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                columnMetaObjectList.add(new ColumnMetaObject(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnMetaObjectList;
    }

    private String createTableDataQuery() {
        String delimiter = ",";
        StringJoiner joiner = new StringJoiner(delimiter);
        for (ColumnMetaObject columnMetaObject : columnMetaObjectList) {
            joiner.add(columnMetaObject.getColumnName());
        }

        return "SELECT " + joiner + " FROM " + tableName;
    }

    private String createColumnNamesQuery() {
        return "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + this.databaseName + "' AND TABLE_NAME = '" + this.tableName + "'";
    }

    private String createColumnPropertiesQuery() {
        return "SELECT\n" +
                "    COLUMN_NAME,\n" +
                "    COLUMN_TYPE,\n" +
                "    IS_NULLABLE,\n" +
                "    COLUMN_KEY,\n" +
                "    COLUMN_DEFAULT,\n" +
                "    EXTRA\n" +
                "FROM\n" +
                "    INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE\n" +
                "    TABLE_SCHEMA = '" + databaseName + "' AND\n" +
                "    TABLE_NAME = '" + tableName + "'";

    }

}

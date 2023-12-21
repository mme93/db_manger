package mamei.backend.datenbank.mariadb.db.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableObject {

    private DatabaseServer databaseServer;
    private List<TableColumn> tableColumns = new ArrayList<>();
    private List<TableMetaRow> tableMetaRows = new ArrayList<>();
    private Connection connection;
    private int tableSize;

    public TableObject builder() {
        return this;
    }

    public TableObject whitConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    public TableObject whitDatabaseServer(DatabaseServer databaseServer) {
        this.databaseServer = databaseServer;
        return this;
    }

    public TableObject withTableSize() {
        tableSize = tableColumns.size();
        return this;
    }

    public TableObject loadTableMetaContext() {
        String query = createTableDataQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int index = 0;
            while (resultSet.next()) {
                tableMetaRows.add(generateTableMetaRow(resultSet, index));
                index++;
            }
            if (!checkTableHasIdCol()) {
                createVisualIdCol();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this;
    }

    private TableMetaRow generateTableMetaRow(ResultSet resultSet, int index) throws SQLException {
        TableMetaRow tableMetaRow = new TableMetaRow();
        tableMetaRow.setIndex(index);
        List<TableMetaColumn> tableMetaColumns = new ArrayList<>();
        for (TableColumn tableColumn : tableColumns) {
            if (matchColumnType(tableColumn.getColumnType(), Integer.class)) {
                TableMetaColumn tableMetaColumn = new TableMetaColumn();
                int result = resultSet.getInt(tableColumn.getColumnName());
                tableMetaColumn.setColumnName(tableColumn.getColumnName());
                tableMetaColumn.setValue(String.valueOf(result));
                tableMetaColumns.add(tableMetaColumn);
            } else if (matchColumnType(tableColumn.getColumnType(), String.class)) {
                TableMetaColumn tableMetaColumn = new TableMetaColumn();
                String result = resultSet.getString(tableColumn.getColumnName());
                tableMetaColumn.setColumnName(tableColumn.getColumnName());
                tableMetaColumn.setValue(result);
                tableMetaColumns.add(tableMetaColumn);
            } else if (matchColumnType(tableColumn.getColumnType(), Float.class)) {
                TableMetaColumn tableMetaColumn = new TableMetaColumn();
                Float result = resultSet.getFloat(tableColumn.getColumnName());
                tableMetaColumn.setColumnName(tableColumn.getColumnName());
                tableMetaColumn.setValue(String.valueOf(result));
                tableMetaColumns.add(tableMetaColumn);
            }else if (matchColumnType(tableColumn.getColumnType(), Enum.class)) {
                TableMetaColumn tableMetaColumn = new TableMetaColumn();
                String result = resultSet.getString(tableColumn.getColumnName());
                tableMetaColumn.setColumnName(tableColumn.getColumnName());
                tableMetaColumn.setValue(String.valueOf(result));
                tableMetaColumns.add(tableMetaColumn);
            } else {
                throw new SQLException("No column typ found from typ: " + tableColumn.getColumnType());
            }
        }
        tableMetaRow.setTableMetaColumns(tableMetaColumns);
        return tableMetaRow;
    }

    public boolean matchColumnType(String columnType, Class typeClass) {
        String[] stringTypes = {"varchar", "text"};
        String[] intTypes = {"bigint","int"};
        String[] floatTypes = {"decimal"};
        String[] enumTypes = {"enum"};
        switch (getTypeClassSimpleName(typeClass)) {
            case "String":
                if (containsColumnTypes(columnType, stringTypes)) return true;
                break;
            case "Integer":
                if (containsColumnTypes(columnType, intTypes)) return true;
                break;
            case "Float":
                if (containsColumnTypes(columnType, floatTypes)) return true;
                break;
            case "Enum":
                if (containsColumnTypes(columnType, enumTypes)) return true;
                break;
            default:
                return false;
        }
        return false;
    }

    private String getTypeClassSimpleName(Class<?> typeClass) {
        return typeClass.getSimpleName();
    }

    private boolean containsColumnTypes(String columnType, String[] columnTypes) {
        for (String existType : columnTypes) {
            if (columnType.contains(existType)) {
                return true;
            }
        }
        return false;
    }

    public TableObject loadTableHeaderContext() {
        String query = createColumnPropertiesQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                loadColumnHeader(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    private boolean checkTableHasIdCol() {
        for (TableColumn column : tableColumns) {
            if (column.getColumnName().equalsIgnoreCase("ID")) {
                return true;
            }
        }
        return false;

    }

    private void createVisualIdCol() {
        String colName="vs_id";
        tableColumns.add(0, new TableColumn(colName, "bigInt()", false, null, null, null));
        int index = 1;
        for (TableMetaRow tableMetaRow : tableMetaRows) {
            tableMetaRow.getTableMetaColumns().add(0, new TableMetaColumn(colName, String.valueOf(index)));
            index++;
        }
    }

    private void loadColumnHeader(ResultSet resultSet) throws SQLException {
        TableColumn tableColumn = new TableColumn();
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            if (i == 1) {
                tableColumn.setColumnName(resultSet.getString(i));
            } else if (i == 2) {
                tableColumn.setColumnType(resultSet.getString(i));
            } else if (i == 3) {
                tableColumn.setNullAble(resultSet.getString(i).equals("NO"));
            } else if (i == 4) {
                tableColumn.setColumnKey(resultSet.getString(i));
            } else if (i == 5) {
                tableColumn.setColumnDefault(resultSet.getString(i));
            } else if (i == 6) {
                tableColumn.setExtra(resultSet.getString(i));
            }
        }
        tableColumns.add(tableColumn);
    }

    public TableObject closeConnection() throws SQLException {
        connection.close();
        return this;
    }

    private String createTableDataQuery() {
        String delimiter = ",";
        StringJoiner joiner = new StringJoiner(delimiter);
        for (TableColumn tableColumn : tableColumns) {
            joiner.add(tableColumn.getColumnName());
        }
        return "SELECT " + joiner + " FROM " + databaseServer.getTableName();
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
                "    TABLE_SCHEMA = '" + databaseServer.getDatabaseName() + "' AND\n" +
                "    TABLE_NAME = '" + databaseServer.getTableName() + "'";

    }

}

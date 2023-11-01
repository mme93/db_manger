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
            if (tableColumn.getColumnType().contains("bigint")) {
                TableMetaColumn tableMetaColumn = new TableMetaColumn();
                int result = resultSet.getInt(tableColumn.getColumnName());
                tableMetaColumn.setColumnName(tableColumn.getColumnName());
                tableMetaColumn.setValue(String.valueOf(result));
                tableMetaColumns.add(tableMetaColumn);
            } else if (tableColumn.getColumnType().contains("varchar")) {
                TableMetaColumn tableMetaColumn = new TableMetaColumn();
                String result = resultSet.getString(tableColumn.getColumnName());
                tableMetaColumn.setColumnName(tableColumn.getColumnName());
                tableMetaColumn.setValue(result);
                tableMetaColumns.add(tableMetaColumn);
            } else {
                throw new SQLException("No column typ found from typ: " + tableColumn.getColumnType());
            }
        }
        tableMetaRow.setTableMetaColumns(tableMetaColumns);
        return tableMetaRow;
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
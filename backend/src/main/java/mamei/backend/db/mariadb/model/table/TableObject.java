package mamei.backend.db.mariadb.model.table;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TableObject {

    private String tableName;

    private String databaseName;

    private String serverName;

    private List<ColumnObject> columnList;

    public TableObject(String tableName, String databaseName, String serverName) {
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.serverName = serverName;
    }

    public void initTable(Connection connection) throws SQLException {
        columnList = getColumnNameList(connection, createColumnNamesQuery());
        columnList = loadColumnProperties(connection,createColumnPropertiesQuery());
        connection.close();
    }

    public void loadTableContext(Connection connection) throws SQLException {

        connection.close();
    }

    private List<ColumnObject> loadColumnProperties(Connection connection, String query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                for(int i=0;i<columnList.size();i++) {
                    System.out.println(resultSet.getString(i+1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnList;
    }


    private List<ColumnObject> getColumnNameList(Connection connection, String query) {
        columnList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int index = 0;
            while (resultSet.next()) {
                columnList.add(new ColumnObject(resultSet.getString(1), index));
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnList;
    }

    private String createColumnNamesQuery() {
        return "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + this.databaseName + "' AND TABLE_NAME = '" + this.tableName + "'";
    }

    private String createColumnPropertiesQuery() {
        return "SELECT\n" +
                "    COLUMN_NAME,\n" +
                "    COLUMN_TYPE,\n" +
                "    IS_NULLABLE\n" +
                "FROM\n" +
                "    INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE\n" +
                "    TABLE_SCHEMA = '"+databaseName+"' AND\n" +
                "    TABLE_NAME = '"+tableName+"'";

    }

}

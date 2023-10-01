package mamei.backend.db.mariadb.model.table;

import mamei.backend.db.mariadb.utility.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public boolean checkTableExist(Connection connection){
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
    private List<ColumnObject> loadColumnProperties(Connection connection, String query) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                for(int i=0;i<columnList.size();i++) {
                    System.out.println(resultSet.getString(i));
                }
            }
            connection.close();
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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnList;
    }

    private String createColumnNamesQuery() {
        return "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + this.tableName + "' AND TABLE_NAME = '" + this.databaseName + "'";
    }

    private String createColumnPropertiesQuery() {
        int listLength=columnList.size();
        String columns="'"+columnList+"', ";
        for(int i=1;i<listLength;i++){
           if(listLength-1==i){
               columns=columns+"'"+columnList;
           }else{
               columns=columns+"'"+columnList+"', ";
           }
        }
        return "SELECT COLUMN_NAME AS 'Spaltenname', COLUMN_TYPE AS 'Datentyp', IS_NULLABLE AS 'Nullable'," +
                "COLUMN_KEY AS 'Schlüsseltyp', COLUMN_DEFAULT AS 'Standardwert', EXTRA AS 'Zusatzinformation'" +
                "FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '"+databaseName+"' AND" +
                "TABLE_NAME = 'IhreTabelle' AND COLUMN_NAME IN ('"+columns+"')";
    }

}

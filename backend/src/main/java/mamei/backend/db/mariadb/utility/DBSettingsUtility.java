package mamei.backend.db.mariadb.utility;

import mamei.backend.db.mariadb.model.table.TIndexObject;
import org.springframework.stereotype.Service;
import mamei.backend.db.mariadb.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBSettingsUtility {

    /**
     *
     * Führt ein {@link DBQuery} für die Datenbank aus und gibt die {@link DBQueryResponse}
     *
     * @param query QueryStatement
     * @param columnIndex Spalten Index
     * @return Response der Query
     * @throws SQLException
     */
    public List<String> preparedStatementValueFormColumnIndex(String query, int columnIndex,Connection connection) throws SQLException {
        List<String>resultList= new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(resultSet.getString(columnIndex));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return resultList;
    }

    public List<String> preparedStatement(List<TIndexObject>indexObjectList, String query) throws SQLException {
        List<String>resultList= new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                for(TIndexObject tIndexObject:indexObjectList){
                    if (tIndexObject.getTClass().equals(String.class) ) {
                        resultList.add(resultSet.getString(tIndexObject.getColumnIndex()));
                    }else if (tIndexObject.getTClass().equals(Integer.class) ) {
                        resultList.add(resultSet.getString(tIndexObject.getColumnIndex()));
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return resultList;
    }

    /**
     *
     * Erstellt eine MariaDB-Verindung her.
     *
     * @return Datenbank-Verindung
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://mameie.ddns.net:3306/db_manager";
        String username = "markus";
        String password = "123";
        return DriverManager.getConnection(url, username, password);
    }

    /**
     *
     * Erstellt eine Datenbank-Verindung her.
     *
     * @return Datenbank-Verindung
     * @throws SQLException
     */
    public Connection getConnection(String database) throws SQLException {
        String url = "jdbc:mysql://mameie.ddns.net:3306/"+database;
        String username = "markus";
        String password = "123";
        return DriverManager.getConnection(url, username, password);
    }

    /**
     *
     * Schließt die Verindung zur Datenbank
     *
     * @param connection Datenbank-Verindung
     * @return Stauts ob die Verbindung geschlossen wurde
     */
    public boolean closeConnection(Connection connection) {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

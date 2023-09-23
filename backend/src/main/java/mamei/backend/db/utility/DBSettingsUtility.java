package mamei.backend.db.utility;

import org.springframework.stereotype.Service;
import mamei.backend.db.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBSettingsUtility {

    /**
     *
     * Erstellt eine Datenbank-Verindung her.
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

    /**
     *
     * Führt ein {@link DBQuery} für die Datenbank aus und gibt die {@link DBQueryResponse}
     *
     * @param query QueryStatement
     * @param columnIndex Spalten Index
     * @return Response der Query
     * @throws SQLException
     */
    public List<String> preparedStatementValueFormColumnIndex(String query, int columnIndex) throws SQLException {
        List<String>resultList= new ArrayList<>();
        Connection connection = getConnection();
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

}

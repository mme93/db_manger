package mamei.backend.db.utility;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class DBSettingsUtility {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://217.160.26.246:3306/mameie";
        String username = "admin";
        String password = "!Mameie93";
        return DriverManager.getConnection(url, username, password);
    }

    public boolean closeConnection(Connection connection) {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String preparedStatement(String insertSql) throws SQLException {
        Connection connection = getConnection();
        StringBuilder sb = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                System.out.println(databaseName);
                sb.append(databaseName + ", ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void preparedStatement(List<String> statements, String insertSql) {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            for (String statement : statements) {
                preparedStatement.setString(1, statement);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

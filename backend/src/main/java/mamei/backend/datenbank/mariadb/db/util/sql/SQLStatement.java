package mamei.backend.datenbank.mariadb.db.util.sql;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class SQLStatement {

    /**
     * Execute query and read one parameter from index.
     *
     * @param query
     * @param connection
     * @param index
     */
    public String executeStatement(String query, Connection connection, int index){
        StringBuilder stringBuilder = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString(index)+"\n");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String executeUpdateStatement(String query, Connection connection) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        Statement statement= null;
        try{
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            statement.close();
            connection.close();
        }
        return stringBuilder.toString();
    }


}

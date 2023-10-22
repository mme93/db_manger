package mamei.backend.datenbank.mariadb.db.util.sql;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}

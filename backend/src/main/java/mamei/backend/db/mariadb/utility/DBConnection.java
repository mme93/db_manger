package mamei.backend.db.mariadb.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@Component
public class DBConnection {

    @Value("${maria_db_1.ip}")
    private String ip;

    @Value("${maria_db_1.user}")
    private String userName;

    @Value("${maria_db_1.password}")
    private String password;

    private String database;
    private boolean isDatabaseConnection;

    Connection createConnection() throws SQLException {
        String url;
        if(isDatabaseConnection){
            url="jdbc:mysql://"+ip+database;
        }else{
            url="jdbc:mysql://"+ip;
        }
        return DriverManager.getConnection(url, userName, password);
    }

    void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

}

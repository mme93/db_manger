package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.assets.DBQueryTableBasic;
import mamei.backend.db.mariadb.utility.DBConnection;
import mamei.backend.db.mariadb.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final DBSettingsUtility dbSettingsUtility;
    private final DBConnection connection;

    public UserService(DBSettingsUtility dbSettingsUtility, DBConnection connection) {
        this.dbSettingsUtility = dbSettingsUtility;
        this.connection = connection;
    }

    public String getUsers(String serverName) throws SQLException {
        return dbSettingsUtility.preparedStatement(
                DBQueryTableBasic.showAllUsers,
                connection.createConnection(serverName)
        );
    }

    public List<String> getPrivilegesFromUser(String user) throws SQLException {
        // return dbSettingsUtility.preparedStatementValueFormColumnIndex(DBQueryTableBasic.showPrivilegesForUser_Local_1 +user+DBQueryTableBasic.showPrivilegesForUser_Local_2,1,dbSettingsUtility.getConnection());
        return null;
    }

    public void updatePrivileges() {
    }

    public void createUser() {
    }

    public void deleteUser() {
    }

    public void changePassword() {
    }
}

package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.assets.DBQueryTableBasic;
import mamei.backend.db.mariadb.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final DBSettingsUtility dbSettingsUtility;

    public UserService(DBSettingsUtility dbSettingsUtility) {
        this.dbSettingsUtility = dbSettingsUtility;
    }

    public List<String> getUsers() throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex(
                DBQueryTableBasic.showAllUsers
                , 1
                , dbSettingsUtility.getConnection()
        );
    }

    public List<String> getPrivilegesFromUser(String user) throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex(DBQueryTableBasic.showPrivilegesForUser_Local_1 +user+DBQueryTableBasic.showPrivilegesForUser_Local_2,1,dbSettingsUtility.getConnection());
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

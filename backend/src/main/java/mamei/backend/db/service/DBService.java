package mamei.backend.db.service;

import mamei.backend.db.assets.DBQueryTableBasic;
import mamei.backend.db.model.DBQuery;
import mamei.backend.db.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DBService {

    private final DBSettingsUtility dbSettingsUtility;

    public DBService(DBSettingsUtility dbSettingsUtility) {
        this.dbSettingsUtility = dbSettingsUtility;
    }

    public List<String> getDatabaseNames() throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex(DBQueryTableBasic.showDatabases,1);
    }

    public List<String> getAllUser() throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex(DBQueryTableBasic.showAllUsers,1);
    }

    public List<String> getPrivilegesFromUser(String user) throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex(DBQueryTableBasic.showPrivilegesForUser_1+user+DBQueryTableBasic.showPrivilegesForUser_2,1);
    }

    public List<String> getAllTablesFromAllDatabases() throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex("",1);
    }
    public List<String> createQuery(DBQuery dbQuery) throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex(dbQuery.getDbQuery(),1);
    }
    public List<String> getAllTablesFromDatabase() throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex("",1);
    }
}

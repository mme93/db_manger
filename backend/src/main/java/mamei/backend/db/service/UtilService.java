package mamei.backend.db.service;

import mamei.backend.db.model.DBQuery;
import mamei.backend.db.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UtilService {

    private final DBSettingsUtility dbSettingsUtility;

    public UtilService(DBSettingsUtility dbSettingsUtility) {
        this.dbSettingsUtility = dbSettingsUtility;
    }




    public List<String> createQuery(DBQuery dbQuery) throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex(dbQuery.getDbQuery(),1, dbSettingsUtility.getConnection());
    }
    public List<String> createQuery(DBQuery dbQuery, String database) throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex(dbQuery.getDbQuery(),1, dbSettingsUtility.getConnection());
    }
}

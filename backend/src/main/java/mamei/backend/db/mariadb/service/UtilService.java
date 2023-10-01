package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.model.DBQuery;
import mamei.backend.db.mariadb.utility.DBConnection;
import mamei.backend.db.mariadb.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UtilService {

    private final DBSettingsUtility dbSettingsUtility;
    private final DBConnection connection;

    public UtilService(DBSettingsUtility dbSettingsUtility, DBConnection connection) {
        this.dbSettingsUtility = dbSettingsUtility;
        this.connection = connection;
    }

    public String createQuery(DBQuery dbQuery) throws SQLException {
        return dbSettingsUtility.preparedStatement(dbQuery.getDbQuery(),connection.createConnection(dbQuery.getServerName()));
    }
    public String createQuery(DBQuery dbQuery, String databaseName) throws SQLException {
        return dbSettingsUtility.preparedStatement(dbQuery.getDbQuery(),connection.createConnection(dbQuery.getServerName()));
    }
}

package mamei.backend.db.service;

import mamei.backend.db.assets.DBSettingsConstants;
import mamei.backend.db.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DBService {

    private final DBSettingsUtility dbSettingsUtility;

    public DBService(DBSettingsUtility dbSettingsUtility) {
        this.dbSettingsUtility = dbSettingsUtility;
    }

    public String getDatabaseNames() throws SQLException {
        return dbSettingsUtility.preparedStatement(DBSettingsConstants.showDatabases);
    }
}

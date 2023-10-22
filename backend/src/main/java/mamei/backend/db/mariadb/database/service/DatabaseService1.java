package mamei.backend.db.mariadb.database.service;

import mamei.backend.db.mariadb.config.utils.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DatabaseService1 {

    private final DBSettingsUtility dbSettingsUtility;

    public DatabaseService1(DBSettingsUtility dbSettingsUtility) {
        this.dbSettingsUtility = dbSettingsUtility;
    }

    public List<String> getAllTablesFromDatabase(String database) throws SQLException {
        return null;
    }

    public List<String> getDatabaseNames() throws SQLException {
        return null;
    }

    public void deleteDatabase(String database) {
    }

    public void createDatabase(String database) {
    }
}

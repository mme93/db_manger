package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.assets.DBQueryTableBasic;
import mamei.backend.db.mariadb.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DatabaseService {

    private final DBSettingsUtility dbSettingsUtility;

    public DatabaseService(DBSettingsUtility dbSettingsUtility) {
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

package mamei.backend.db.service;

import mamei.backend.db.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

@Service
public class DBTableService {

    private final DBSettingsUtility dbSettingsUtility;

    public DBTableService(DBSettingsUtility dbSettingsUtility) {
        this.dbSettingsUtility = dbSettingsUtility;
    }

    public void getAllTablesFromDatabase(String database){

    }
    public void getTableFromDatabase(String database, String table){

    }
    public void createTable(String database, String table){

    }
    public void dropTable(String database, String table){

    }
    public void updateTable(){

    }


}

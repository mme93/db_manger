package mamei.backend.db.mariadb.service;

import mamei.backend.db.mariadb.utility.DBSettingsUtility;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TableService {

    private final DBSettingsUtility dbSettingsUtility;

    public TableService(DBSettingsUtility dbSettingsUtility) {
        this.dbSettingsUtility = dbSettingsUtility;
    }

    public List<String> getAllTablesFromAllDatabases() throws SQLException {
        return dbSettingsUtility.preparedStatementValueFormColumnIndex("",1,dbSettingsUtility.getConnection());
    }

    public List<String> getAllTablesFromDatabase(String database){
        return null;
    }
    public void getTableFromDatabase(String database, String table){

    }
    public void createTable(String database, String table){

    }
    public void dropTable(String database, String table){

    }
    public void getTableInformation(String database, String table){

    }
    public void addDataToTable(){

    }
    public void removeDataFromTable(){

    }

}

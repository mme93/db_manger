package mamei.backend.datenbank.mariadb.db.util.sqlgenerator;

import org.springframework.stereotype.Service;

@Service
public class ServerQueryGenerator {

    public String showAllDatabaseFromServer(){
        return "SHOW DATABASES";
    }
    public String createDatabaseQuery(String databaseName){
        return "CREATE DATABASE IF NOT EXISTS "+databaseName;
    }
    public String deleteDatabaseQuery(String databaseName){
        return "DROP DATABASE IF EXISTS "+databaseName;
    }
}

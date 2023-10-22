package mamei.backend.datenbank.mariadb.db.service;

import mamei.backend.datenbank.mariadb.db.util.ServerQueryGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {

    private final ServerQueryGenerator serverQueryGenerator;

    public DatabaseService(ServerQueryGenerator serverQueryGenerator) {
        this.serverQueryGenerator = serverQueryGenerator;
    }

    public List<String> getDatabaseNameByServer(String serverName){
        List<String>databaseNameList= new ArrayList<>();

        return databaseNameList;
    }
}

package mamei.backend.datenbank.mariadb.db.util;

import org.springframework.stereotype.Service;

@Service
public class ServerQueryGenerator {

    public String showAllDatabaseFromServer(String servername){
        return "SHOW DATABASES";
    }
}

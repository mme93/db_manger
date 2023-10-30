package mamei.backend.datenbank.mariadb.db.util.sqlgenerator;

import org.springframework.stereotype.Service;

@Service
public class TableQueryGenerator {

    public String generateQueryAllTableNamesFromDatabase(){
        return "SHOW TABLES";
    }

}

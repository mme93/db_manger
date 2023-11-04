package mamei.backend.datenbank.mariadb.db.util.validator;

import mamei.backend.datenbank.mariadb.db.model.report.TableCreateReport;
import org.springframework.stereotype.Service;

@Service
public class TableValidator {

    public boolean isCreateTableValid(){

        return true;
    }


    public TableCreateReport generateCreateTableReport(){
        return new TableCreateReport();
    }

}

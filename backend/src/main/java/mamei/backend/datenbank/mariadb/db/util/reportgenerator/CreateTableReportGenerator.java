package mamei.backend.datenbank.mariadb.db.util.reportgenerator;

import mamei.backend.datenbank.mariadb.db.model.report.TableCreateReport;
import org.springframework.stereotype.Service;

@Service
public class CreateTableReportGenerator {

    public TableCreateReport generateCreateTableReport(){
        return new TableCreateReport();
    }
}

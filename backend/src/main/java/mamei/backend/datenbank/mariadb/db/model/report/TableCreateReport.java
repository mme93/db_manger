package mamei.backend.datenbank.mariadb.db.model.report;

import lombok.Getter;
import lombok.Setter;
import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;

import java.util.List;

@Getter
@Setter
public class TableCreateReport {
    private boolean isValid;
    private String tableName;
    private List<TableColumn> tableMetaColumnList;
    //Checks
    private boolean isTableNameValid;
    private boolean isTableValid;
    private boolean isKeyValid;
    //Report
    private String tableNameReport;
    private String keyReport;
}

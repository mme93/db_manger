package mamei.backend.datenbank.mariadb.db.model.report;

import lombok.Getter;
import lombok.Setter;
import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;

import java.util.List;

@Getter
@Setter
public class TableCreateReport {
    private String tableName;
    private List<TableColumn> tableMetaColumnList;
    //Checks
    private boolean isTableNameValid;
    private boolean isTableValid;
    private boolean isKeyValid;
    private boolean isColumnNameValid;
    //Report
    private String tableNameReport;
    private String keyReport;
    private List<String> columnNameIsEmptyReport;
    private List<String> columnNameRuleReport;
    private String sqlExceptionReport;
}

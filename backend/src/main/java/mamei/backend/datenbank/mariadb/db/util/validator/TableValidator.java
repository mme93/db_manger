package mamei.backend.datenbank.mariadb.db.util.validator;

import mamei.backend.datenbank.mariadb.db.model.report.TableCreateReport;
import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableValidator {

    public TableCreateReport isCreateTableValid(List<TableColumn> tableMetaColumnList, String tableName) {
        TableCreateReport report = new TableCreateReport();
        report.setTableName(tableName);
        report = checkTableName(report);
        hasPrimaryKey(tableMetaColumnList);
        return report;
    }

    private TableCreateReport checkTableName(TableCreateReport report) {
        String tableNameReport = "";
        report.setTableNameValid(true);
        if (tableNameAllReadyExist()) {
            tableNameReport = "Table all ready exist. \n";
            report.setTableNameValid(false);
        }
        if (!checkTableNamRules(report.getTableName())) {
            tableNameReport=generateTableNameImprovement(tableNameReport,report.getTableName());
            report.setTableNameValid(false);
        }

        report.setTableNameReport(tableNameReport);
        return report;
    }

    private String generateTableNameImprovement(String tableNameReport, String tableName) {
        tableNameReport=tableNameReport+"Problems with Table name Convention, here is one improvement exampel: \n";
        tableNameReport=tableNameReport+"New Table Name: "+tableName.replaceAll(" ","_").toLowerCase();
        return tableNameReport;
    }

    private boolean checkTableNamRules(String tableName) {
        List<Character> charList = new ArrayList<>();
        for (char c : tableName.toCharArray()) {
            charList.add(c);
        }
        return !(tableName.contains(" ") && (charList.stream().filter(c -> Character.isUpperCase(c)).count()>0));
    }

    private boolean tableNameAllReadyExist() {

        return true;
    }

    private boolean hasPrimaryKey(List<TableColumn> tableMetaColumnList) {
        return (tableMetaColumnList.stream().filter(tableColumn -> tableColumn.getColumnKey().equals("PRIMARY")).count() == 1);
    }

    private boolean checkAutoIncrementRules() {

        return true;
    }

    private boolean checkColNameRules() {

        return true;
    }

}

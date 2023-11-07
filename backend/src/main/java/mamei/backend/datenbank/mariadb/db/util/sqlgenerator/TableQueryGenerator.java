package mamei.backend.datenbank.mariadb.db.util.sqlgenerator;

import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;
import mamei.backend.datenbank.mariadb.db.model.table.TableCreate;
import org.springframework.stereotype.Service;

@Service
public class TableQueryGenerator {

    public String generateQueryAllTableNamesFromDatabase() {
        return "SHOW TABLES";
    }

    public String generateQueryCreateTable(TableCreate tableCreate) {
        StringBuilder queryBuilder= new StringBuilder();
        queryBuilder.append("CREATE TABLE " + tableCreate.getDatabaseServer().getTableName() + " ( \n");
        for (int i = 0; i < tableCreate.getTableMetaColumnList().size(); i++) {
            TableColumn tableColumn = tableCreate.getTableMetaColumnList().get(i);
            if (tableColumn.getColumnName() != null && !tableColumn.getColumnName().isEmpty()) {
                queryBuilder.append(tableColumn.getColumnName().toUpperCase() + "  ");
            }
            if (tableColumn.getColumnType() != null && !tableColumn.getColumnType().isEmpty()) {
                queryBuilder.append(tableColumn.getColumnType() + "  ");
            }
            if (tableColumn.getExtra() != null && !tableColumn.getExtra().isEmpty()) {
                queryBuilder.append(tableColumn.getExtra() + "  ");
            }
            if (tableColumn.getColumnDefault() != null && !tableColumn.getColumnDefault().isEmpty()) {
                queryBuilder.append("DEFAULT '"+tableColumn.getColumnDefault() + "'  ");
            }
            if (tableColumn.getColumnKey() != null && !tableColumn.getColumnKey().isEmpty()) {
                queryBuilder.append(tableColumn.getColumnKey() + " KEY  ");
            }
            if (!tableColumn.isNullAble()) {
                queryBuilder.append("NOT NULL");
            }

            if (tableCreate.getTableMetaColumnList().size() != i + 1) {
                queryBuilder.append(" ,\n");
            }else{
                queryBuilder.append(" \n )");
            }
        }
        return queryBuilder.toString();
    }
}

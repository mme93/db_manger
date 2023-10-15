package mamei.backend.db.mariadb.table.utils;

import mamei.backend.db.mariadb.table.model.object.ColumnMetaObject;
import mamei.backend.db.mariadb.table.model.object.TableColumnDataInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * Create Query-Strings for Table information und interaction.
 */
@Service
public class TableQueryGenerator {

    public String createTableQuery(List<ColumnMetaObject> columnList, String tableName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE " + tableName + " ( ");
        for (int i = 0; i < columnList.size(); i++) {
            if (i != columnList.size() - 1) {
                stringBuilder.append(createRowQuery(columnList.get(i)) + ", \n");
                if (columnList.get(i).getCOLUMN_KEY() != null && !columnList.get(i).getCOLUMN_KEY().toLowerCase(Locale.ROOT).contains("null")) {
                    stringBuilder.append("PRIMARY KEY (" + columnList.get(i).getColumnName() + "), \n");
                }
            } else {
                stringBuilder.append(createRowQuery(columnList.get(i)) + "\n )");
                if (columnList.get(i).getCOLUMN_KEY() != null && !columnList.get(i).getCOLUMN_KEY().toLowerCase(Locale.ROOT).contains("null")) {
                    stringBuilder.append("PRIMARY KEY (" + columnList.get(i).getColumnName() + ")\n )");
                }
            }
        }
        return stringBuilder.toString();
    }

    public String createRowQuery(ColumnMetaObject columnMetaObject) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(columnMetaObject.getColumnName() + " ");

        if (columnMetaObject.getCOLUMN_TYPE() != null && !columnMetaObject.getCOLUMN_TYPE().toLowerCase(Locale.ROOT).contains("null")) {
            stringBuilder.append(columnMetaObject.getCOLUMN_TYPE() + " ");
        }
        if (columnMetaObject.isIS_NULLABLE()) {
            stringBuilder.append("NULL ");
        } else if (!columnMetaObject.isIS_NULLABLE()) {
            stringBuilder.append("NOT NULL ");
        }
        if (columnMetaObject.getCOLUMN_DEFAULT() != null && !columnMetaObject.getCOLUMN_DEFAULT().toLowerCase(Locale.ROOT).contains("null")) {
            stringBuilder.append(columnMetaObject.getCOLUMN_DEFAULT() + " ");
        }
        if (columnMetaObject.getEXTRA() != null && !columnMetaObject.getEXTRA().toLowerCase(Locale.ROOT).contains("null")) {
            stringBuilder.append(columnMetaObject.getEXTRA() + " ");
        }
        return stringBuilder.toString();
    }

    public String createInsertQuery(String tableName, List<TableColumnDataInfo> tableDataSetObjs) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO " + tableName + " (");
        for (int i = 0; i < tableDataSetObjs.size(); i++) {
            queryBuilder.append(tableDataSetObjs.get(i).getColumnName());
            if (i != tableDataSetObjs.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(") VALUES (");
        for (int i = 0; i < tableDataSetObjs.size(); i++) {
            if (tableDataSetObjs.get(i).isString()) {
                queryBuilder.append("'" + tableDataSetObjs.get(i).getColumnName() + "'");
            } else if (!tableDataSetObjs.get(i).isString()) {
                queryBuilder.append(tableDataSetObjs.get(i).getColumnName());
            }
            if (i != tableDataSetObjs.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(" )");
        return queryBuilder.toString();
    }

    public String createUpdateQuery(List<TableColumnDataInfo> tableColumnDataInfoList, String tableName) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE "+tableName+" SET");
        for (int i = 0; i < tableColumnDataInfoList.size(); i++) {
            if (tableColumnDataInfoList.get(i).isString()) {
                queryBuilder.append(tableColumnDataInfoList.get(i).getColumnName()+" = '" + tableColumnDataInfoList.get(i).getValue() + "'");
            } else if (!tableColumnDataInfoList.get(i).isString()) {
                queryBuilder.append(tableColumnDataInfoList.get(i).getColumnName());
            }
            if (i != tableColumnDataInfoList.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(" WHERE id = "+tableColumnDataInfoList.get(0).getId());
        return queryBuilder.toString();
    }

}

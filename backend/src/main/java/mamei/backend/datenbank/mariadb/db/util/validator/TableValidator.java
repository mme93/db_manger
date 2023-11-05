package mamei.backend.datenbank.mariadb.db.util.validator;

import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableValidator {

    public boolean isCreateTableValid(List<TableColumn> tableMetaColumnList){

        return hasPrimaryKey(tableMetaColumnList);
    }

    private boolean hasPrimaryKey(List<TableColumn> tableMetaColumnList){
        return (tableMetaColumnList.stream().filter(tableColumn -> tableColumn.getColumnKey().equals("PRIMARY")).count() ==1);
    }

}

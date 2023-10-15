package mamei.backend.db.mariadb.table.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mamei.backend.db.mariadb.table.model.object.ColumnMetaObject;
import mamei.backend.db.mariadb.table.model.object.ColumnRowObject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VTableObject {

    private String tableName;

    private String databaseName;

    private String serverName;

    private List<ColumnMetaObject> columnList;

    private List<ColumnRowObject> columnRowList;

}
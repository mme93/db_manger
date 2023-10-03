package mamei.backend.db.mariadb.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VTableObject {

    private String tableName;

    private String databaseName;

    private String serverName;

    private List<ColumnMetaObject> columnList;

}

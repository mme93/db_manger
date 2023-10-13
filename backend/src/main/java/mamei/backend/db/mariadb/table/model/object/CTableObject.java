package mamei.backend.db.mariadb.table.model.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CTableObject {

    private String tableName;

    private String databaseName;

    private String serverName;

    private List<ColumnMetaObject> columnList;
}

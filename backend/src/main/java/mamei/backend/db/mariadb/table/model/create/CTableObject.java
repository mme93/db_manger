package mamei.backend.db.mariadb.table.model.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mamei.backend.db.mariadb.config.model.DBServer;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CTableObject {

    private DBServer dbServer;

    private List<CColumnMetaObject> columnList;
}

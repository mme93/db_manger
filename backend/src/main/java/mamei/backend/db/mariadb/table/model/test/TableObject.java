package mamei.backend.db.mariadb.table.model.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mamei.backend.db.mariadb.table.model.DBServer;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TableObject {

    private DBServer dbServer;

    private List<TableRowObject>tableRowObjectList;

}

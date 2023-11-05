package mamei.backend.datenbank.mariadb.db.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableCreate {

    private DatabaseServer databaseServer;
    private List<TableColumn> tableMetaColumnList;

}

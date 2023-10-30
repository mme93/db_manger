package mamei.backend.datenbank.mariadb.db.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableView {

    private String tableName;

    private int tableSize;

    private List<TableColumn>tableColumns;

    private List<TableMetaColumn>tableMetaColumns;

}

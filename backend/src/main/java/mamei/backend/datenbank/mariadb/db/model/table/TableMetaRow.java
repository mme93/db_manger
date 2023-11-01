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
public class TableMetaRow {

    private int index;
    private List<TableMetaColumn>tableMetaColumns;
}

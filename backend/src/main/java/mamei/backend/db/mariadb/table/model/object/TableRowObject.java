package mamei.backend.db.mariadb.table.model.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TableRowObject {

    private Long id;

    private List<TableObject>tableObjectList;

}

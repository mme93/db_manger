package mamei.backend.db.mariadb.table.model.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TableColObject {

    private String columnName;

    private String columnType;

    private String value;

}

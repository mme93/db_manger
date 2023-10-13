package mamei.backend.db.mariadb.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TableColumnDataInfo {

    private String columnName;

    private String value;

    private boolean isString;

    private Long id;

}

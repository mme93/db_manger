package mamei.backend.db.mariadb.model.table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnDataObject {

    private String columnName;
    private Object columnContext;

    public ColumnDataObject(String columnName, Object columnContext) {
        this.columnName = columnName;
        this.columnContext = columnContext;
    }
}

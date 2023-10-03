package mamei.backend.db.mariadb.model.table;

import lombok.Getter;
import lombok.Setter;
import mamei.backend.db.mariadb.model.ENUM.MySQLDataType;

@Getter
@Setter
public class ColumnObject{

    private String columnName;
    private Enum<MySQLDataType> mySQLDataTypeEnum;
    private Object columnContext;
    private int index;
    private String COLUMN_TYPE;
    private boolean IS_NULLABLE;
    private String COLUMN_KEY;
    private String COLUMN_DEFAULT;
    private String EXTRA;

    public ColumnObject(String columnName,int index) {
        this.columnName = columnName;
        this.index=index;
    }
}

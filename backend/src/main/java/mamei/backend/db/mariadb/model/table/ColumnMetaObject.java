package mamei.backend.db.mariadb.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mamei.backend.db.mariadb.model.ENUM.MySQLDataType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColumnMetaObject {

    private String columnName;
    private Enum<MySQLDataType> mySQLDataTypeEnum;
    private String COLUMN_TYPE;
    private boolean IS_NULLABLE;
    private String COLUMN_KEY;
    private String COLUMN_DEFAULT;
    private String EXTRA;

    public ColumnMetaObject(String columnName) {
        this.columnName = columnName;
    }
}

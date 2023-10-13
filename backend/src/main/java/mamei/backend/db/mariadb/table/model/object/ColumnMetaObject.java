package mamei.backend.db.mariadb.table.model.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mamei.backend.db.mariadb.table.model.ENUM.MySQLDataType;

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

    public ColumnMetaObject(String columnName, String COLUMN_TYPE, boolean IS_NULLABLE, String COLUMN_KEY, String COLUMN_DEFAULT, String EXTRA) {
        this.columnName = columnName;
        this.COLUMN_TYPE = COLUMN_TYPE;
        this.IS_NULLABLE = IS_NULLABLE;
        this.COLUMN_KEY = COLUMN_KEY;
        this.COLUMN_DEFAULT = COLUMN_DEFAULT;
        this.EXTRA = EXTRA;
    }
}

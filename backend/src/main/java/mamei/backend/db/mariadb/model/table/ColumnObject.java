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

    public ColumnObject(String columnName,int index) {
        this.columnName = columnName;
        this.index=index;
    }
}

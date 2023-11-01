package mamei.backend.datenbank.mariadb.db.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableColumn {

    private String columnName;
    private String columnType;
    private boolean nullAble;
    private String columnKey;
    private String columnDefault;
    private String extra;

}

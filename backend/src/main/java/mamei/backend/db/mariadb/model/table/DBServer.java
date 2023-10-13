package mamei.backend.db.mariadb.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DBServer {

    private String serverName;

    private String databaseName;

    private String tableName;

}

package mamei.backend.db.mariadb.table.model;

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

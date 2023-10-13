package mamei.backend.db.mariadb.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DBQuery {

    private String serverName;

    private String databaseName;

    private String dbQuery;

    public DBQuery(String serverName, String dbQuery) {
        this.serverName = serverName;
        this.dbQuery = dbQuery;
    }
}

package mamei.backend.datenbank.mariadb.db.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DatabaseServer {

    private String serverName;

    private String databaseName;

    private String tableName;

}

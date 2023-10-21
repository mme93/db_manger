package mamei.backend.db.mariadb.html.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataBaseObj {

    private String databaseName;
    private List<TableObj>tables;

}

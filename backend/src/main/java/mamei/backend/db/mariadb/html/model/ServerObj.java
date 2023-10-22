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
public class ServerObj {

    private String value;
    private String name;
    private List<DataBaseObj> dataBaseObjs;

}

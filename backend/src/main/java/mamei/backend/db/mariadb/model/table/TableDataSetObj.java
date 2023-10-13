package mamei.backend.db.mariadb.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TableDataSetObj {

   private List<List<TableColumnDataInfo>>tableColumnDataInfoList;

   private DBServer dbServer;


}

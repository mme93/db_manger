package mamei.backend.db.mariadb.table.model.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mamei.backend.db.mariadb.config.model.DBServer;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TableDataSetObj {

   private List<List<TableColumnDataInfo>>tableColumnDataInfoList;

   private DBServer dbServer;


}

package mamei.backend.datenbank.mariadb.db.controller;

import mamei.backend.datenbank.mariadb.db.model.report.TableCreateReport;
import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;
import mamei.backend.datenbank.mariadb.db.model.table.TableCreate;
import mamei.backend.datenbank.mariadb.db.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restTable")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/validate/{tableName}")
    public ResponseEntity<TableCreateReport> validateTable(@RequestBody List<TableColumn> tableMetaColumnList, @PathVariable String tableName){
        TableCreateReport report = tableService.validateCreatTable(tableMetaColumnList,tableName);
        if (report.isValid()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(report, HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<TableCreateReport> validateTables(@RequestBody TableCreate tableCreate){
        System.out.println(tableCreate.getDatabaseServer().getServerName());
        System.out.println(tableCreate.getDatabaseServer().getDatabaseName());
        System.out.println(tableCreate.getDatabaseServer().getTableName());
        return new ResponseEntity<>( HttpStatus.OK);
    }

}

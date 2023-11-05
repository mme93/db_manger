package mamei.backend.datenbank.mariadb.db.controller;

import mamei.backend.datenbank.mariadb.db.model.report.TableCreateReport;
import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;
import mamei.backend.datenbank.mariadb.db.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("restTable")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/validate")
    public ResponseEntity<TableCreateReport> validateTable(@RequestBody List<TableColumn>tableMetaColumnList){
        TableCreateReport report = tableService.validateCreatTable(tableMetaColumnList);
        if (report.isValid()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(report, HttpStatus.CONFLICT);
        }
    }

}

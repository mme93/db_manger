package mamei.backend.datenbank.mariadb.db.controller;

import mamei.backend.datenbank.mariadb.db.model.report.TableCreateReport;
import mamei.backend.datenbank.mariadb.db.model.table.TableCreate;
import mamei.backend.datenbank.mariadb.db.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("restTable")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/validate")
    public ResponseEntity<TableCreateReport> validateTables(@RequestBody TableCreate tableCreate) throws SQLException {
        return new ResponseEntity<>(tableService .validateCreatTable(tableCreate), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Boolean> createTables(@RequestBody TableCreate tableCreate) throws SQLException {
        return new ResponseEntity<>(tableService .createTable(tableCreate), HttpStatus.OK);
    }

}

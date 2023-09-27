package mamei.backend.db.controller;

import mamei.backend.db.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/{database}/tables")
    public ResponseEntity<List<String>> getAllTablesFromDatabase(@PathVariable String database) {
        return new ResponseEntity<>(tableService.getAllTablesFromDatabase(database), HttpStatus.OK);
    }

    @GetMapping("/database/table")
    public ResponseEntity<List<String>> getAllTablesFromAllDatabases() {
        try {
            return new ResponseEntity<>(tableService.getAllTablesFromAllDatabases(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}

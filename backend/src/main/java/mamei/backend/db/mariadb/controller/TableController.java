package mamei.backend.db.mariadb.controller;

import mamei.backend.db.mariadb.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public void createTable(){
        tableService.createTable(null,null);
    }

    @DeleteMapping("/delete")
    public void deleteTable(){
        tableService.dropTable(null,null);
    }

    @PostMapping("/createData")
    public void addDataToTable(){
        tableService.addDataToTable();
    }

    @DeleteMapping("/deleteData")
    public void removeDataFromTable(){
        tableService.removeDataFromTable();
    }

    @GetMapping("/information")
    public void getTableInformation() {
        tableService.getTableInformation(null,null);
    }

    @GetMapping("/information/{database}")
    public void getTableFromDatabase(@PathVariable String database) {
        tableService.getTableFromDatabase(database,null);
    }

    @GetMapping("/test")
    public void test() throws SQLException {
        tableService.getColumnNameFromTable();
    }
}

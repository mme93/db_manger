package mamei.backend.db.mariadb.controller;

import mamei.backend.db.mariadb.model.table.CTableObject;
import mamei.backend.db.mariadb.model.table.TableObject;
import mamei.backend.db.mariadb.model.table.VTableObject;
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
    public ResponseEntity<String> createTable(@RequestBody CTableObject tableObject){
        try {
            return new ResponseEntity(tableService.createTable(tableObject),HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
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
    public ResponseEntity<List<String>> getTableFromDatabase(@PathVariable String database) {
        return new ResponseEntity<>(tableService.getTableFromDatabase(database), HttpStatus.OK);
    }

    @GetMapping("/test/{tableName}/{databaseName}/{serverName}")
    public ResponseEntity<VTableObject> test(@PathVariable String tableName, @PathVariable String databaseName, @PathVariable String serverName) {
        try {
            if(tableService.checkTableExist(databaseName,tableName,serverName)){
                try {
                    return new ResponseEntity<>(tableService.getTableContext(tableName,databaseName,serverName),HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

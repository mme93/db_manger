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

/**
 * Controller for Table Information and functionality.
 */
@RestController
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    /**
     * TODO: Vorher prüfen ob ich Tabelle vorhanden ist
     *
     * Create a new Table.
     *
     * @param tableObject
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity createTable(@RequestBody CTableObject tableObject) {
        try {
            if (!tableService.checkTableExist(tableObject)) {
                tableService.createTable(tableObject);
                return new ResponseEntity(HttpStatus.CREATED);
            }
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    /**
     * Delete Table from Database.
     *
     * @param database
     * @param tableName
     * @param serverName
     */
    @DeleteMapping("/delete/{database}/{tableName}/{serverName}")
    public void deleteTable(@PathVariable String database, @PathVariable String tableName, @PathVariable String serverName) {
        try {
            tableService.dropTable(database, tableName,serverName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/update")
    public void updateTable() {
        tableService.addDataToTable();
    }

    @PostMapping("/createData")
    public void addDataToTable() {
        tableService.addDataToTable();
    }

    @DeleteMapping("/deleteData")
    public void removeDataFromTable() {
        tableService.removeDataFromTable();
    }

    /**
     * Create Table with Context from specific Database.
     *
     * @param tableObject
     * @return VTableObject
     */
    @PostMapping("/information")
    public ResponseEntity<VTableObject> getTableInformation(@RequestBody CTableObject tableObject) {
        try {
            if (tableService.checkTableExist(tableObject)) {
                try {
                    return new ResponseEntity<>(tableService.getTableContext(tableObject), HttpStatus.OK);
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

    /**
     * Create all Tables with Context, which contains in the Database.
     *
     * @param database
     * @param serverName
     * @return
     */
    @GetMapping("/{database}/{serverName}/tables")
    public ResponseEntity<List<VTableObject>> getAllTablesFromDatabase(@PathVariable String database, @PathVariable String serverName) {
        try {
            return new ResponseEntity<>(tableService.getAllTablesFromDatabase(database, serverName), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

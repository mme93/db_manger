package mamei.backend.db.mariadb.table.controller;

import mamei.backend.db.mariadb.table.model.object.CTableObject;
import mamei.backend.db.mariadb.table.model.object.TableColumnDataInfo;
import mamei.backend.db.mariadb.table.model.object.TableDataSetObj;
import mamei.backend.db.mariadb.table.model.view.VTableObject;
import mamei.backend.db.mariadb.table.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static java.util.Arrays.asList;

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
    public ResponseEntity deleteTable(@PathVariable String database, @PathVariable String tableName, @PathVariable String serverName) {
        try {
            tableService.dropTable(database, tableName,serverName);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateTable(@RequestBody TableDataSetObj tableDataSetObj) {
        try {
            tableService.updateDataSet(tableDataSetObj);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/createData/{database}/{tableName}/{serverName}")
    public ResponseEntity addDataToTable(@PathVariable String database, @PathVariable String serverName, @PathVariable String tableName,@RequestBody List<TableColumnDataInfo>tableDataSetObjs) {
        try {
            tableService.addDataToTable( serverName, database,  tableName, tableDataSetObjs);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    /**
     * TODO: Muss noch click-test gemacht werden
     * Delete
     *
     * @param database
     * @param id
     * @param serverName
     * @param tableName
     * @return
     */
    @DeleteMapping("/deleteData/{database}/{tableName}/{serverName}/{id}")
    public ResponseEntity removeDatasetFromTable(@PathVariable String database, @PathVariable String id, @PathVariable String serverName, @PathVariable String tableName) {
        try {
            tableService.removeDataSetFromTable(database,id,serverName,tableName);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    /**
     * TODO: Muss noch click-test gemacht werden
     * Delete
     *
     * @param database
     * @param iDs
     * @param serverName
     * @param tableName
     * @return
     */
    @DeleteMapping("/deleteData/{database}/{tableName}/{serverName}/{iDs}")
    public ResponseEntity removeDataSetsFromTable(@PathVariable String database, @PathVariable String iDs, @PathVariable String serverName, @PathVariable String tableName) {
        try {
            tableService.removeDataSetsFromTable(database,asList(iDs.split(",")),serverName,tableName);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    /**
     * Load Table with Context from specific Database.
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
     * Load all Tables with Context, which contains in the Database.
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

package mamei.backend.db.mariadb.table.controller;

import mamei.backend.db.mariadb.config.model.DBServer;
import mamei.backend.db.mariadb.table.model.create.CTableObject;
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
     * @param dbServer
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteTable(@RequestBody DBServer dbServer) {
        try {
            tableService.dropTable(dbServer);
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
     * Delete dataset from Table by Id.
     *
     * @param dbServer
     * @param id
     * @return
     */
    @DeleteMapping("/deleteData/{id}")
    public ResponseEntity removeDatasetFromTable(@RequestBody DBServer dbServer, @PathVariable String id) {
        try {
            tableService.removeDataSetFromTable(dbServer,id);
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
     * @param dbServer
     * @param iDs
     * @return
     */
    @DeleteMapping("/deleteData/{iDs}")
    public ResponseEntity removeDataSetsFromTable(@RequestBody DBServer dbServer, @PathVariable String iDs) {
        try {
            tableService.removeDataSetsFromTable(dbServer,asList(iDs.split(",")));
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
     * @return {@link List<VTableObject>}
     */
    @GetMapping("/all")
    public ResponseEntity<List<VTableObject>> getAllTablesFromDatabase(@RequestBody DBServer dbServer) {
        try {
            return new ResponseEntity<>(tableService.getAllTablesFromDatabase(dbServer.getDatabaseName(), dbServer.getServerName()), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

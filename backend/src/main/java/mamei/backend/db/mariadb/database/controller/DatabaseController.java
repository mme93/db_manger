package mamei.backend.db.mariadb.database.controller;

import mamei.backend.db.mariadb.database.service.DatabaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 *  Information about Databases and his Tables
 */
@RestController
@RequestMapping("/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    /**
     * @param databaseService Service for prepareStatements for Databases
     */
    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    /**
     *
     * @param database Database Name
     * @return
     */
    @GetMapping("/{database}/tables")
    public ResponseEntity<List<String>> getAllTablesFromDatabase(@PathVariable String database) {
        try {
            return new ResponseEntity<>(databaseService.getAllTablesFromDatabase(database), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/databases")
    public ResponseEntity<List<String>> getAllDatabases() {
        try {
            return new ResponseEntity<>(databaseService.getDatabaseNames(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/create/{database}")
    public ResponseEntity<String> createDatabase(@PathVariable String database){
        databaseService.createDatabase(database);
        return new ResponseEntity<>(database, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{database}")
    public ResponseEntity<String> deleteDatabase(@PathVariable String database){
        databaseService.deleteDatabase(database);
        return new ResponseEntity<>(database, HttpStatus.OK);
    }
}

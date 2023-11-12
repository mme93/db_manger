package mamei.backend.datenbank.mariadb.db.controller;

import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.db.service.DatabaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    //TODO: Prüfen ob das Postmapping verwendet wird? Müsste eigentlich beim ehmalig Server aussuchen verwendet werden.
    @PostMapping("/{serverName}/names")
    public ResponseEntity<List<String>> getDatabaseNameByServer(@PathVariable String serverName) {
        try {
            return new ResponseEntity<>(databaseService.getDatabaseNameByServer(serverName), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createDatabase(@RequestBody DatabaseServer databaseServer) {
        try {
            return new ResponseEntity<>(databaseService.createDatabase(databaseServer), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteDatabase(@RequestBody DatabaseServer databaseServer) {
        try {
            return new ResponseEntity<>(databaseService.deleteDatabase(databaseServer), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}

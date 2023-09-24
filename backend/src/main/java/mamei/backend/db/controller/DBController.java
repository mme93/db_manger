package mamei.backend.db.controller;


import mamei.backend.db.model.DBQuery;
import mamei.backend.db.service.DBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/general")
public class DBController {

    private final DBService dbService;

    public DBController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/helloWorld")
    public ResponseEntity<String>getHelloWorld(){
        return new ResponseEntity<>("HellWorld",HttpStatus.OK);
    }

    @GetMapping("/databases")
    public ResponseEntity<List<String>> getAllDatabases() {
        try {
            return new ResponseEntity<>(dbService.getDatabaseNames(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<String>> getAllUser() {
        try {
            return new ResponseEntity<>(dbService.getAllUser(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/privileges/{user}")
    public ResponseEntity<List<String>> getPrivilegesFromUser(@PathVariable String user) {
        try {
            return new ResponseEntity<>(dbService.getPrivilegesFromUser(user), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/database/table")
    public ResponseEntity<List<String>> getAllTablesFromAllDatabases() {
        try {
            return new ResponseEntity<>(dbService.getAllTablesFromAllDatabases(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{database}/table")
    public ResponseEntity<List<String>> getAllTablesFromDatabase(@PathVariable String database) {
        try {
            return new ResponseEntity<>(dbService.getAllTablesFromDatabase(database), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/createQuery")
    public ResponseEntity<List<String>> createQuery(@RequestBody DBQuery dbQuery){
        try {
            return new ResponseEntity<>(dbService.createQuery(dbQuery), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/createQuery/{database}")
    public ResponseEntity<List<String>> createQueryWithDB(@RequestBody DBQuery dbQuery, @PathVariable String database){
        try {
            return new ResponseEntity<>(dbService.createQuery(dbQuery,database), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}

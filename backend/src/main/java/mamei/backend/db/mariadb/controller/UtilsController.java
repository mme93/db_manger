package mamei.backend.db.mariadb.controller;


import mamei.backend.db.mariadb.model.DBQuery;
import mamei.backend.db.mariadb.service.UtilService;
import mamei.backend.db.mariadb.utility.DBConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/utils")
public class UtilsController {

    private final UtilService utilService;
    private final DBConnection connection;

    public UtilsController(UtilService utilService, DBConnection connection) {
        this.utilService = utilService;
        this.connection = connection;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> getPing() {
        return new ResponseEntity<>("ping", HttpStatus.OK);
    }

    @PostMapping("/createQuery")
    public ResponseEntity<String> createQuery(@RequestBody DBQuery dbQuery) {
        try {
            return new ResponseEntity<>(utilService.createQuery(dbQuery), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/createQuery/{database}")
    public ResponseEntity<String> createQueryWithDB(@RequestBody DBQuery dbQuery, @PathVariable String database) {
        try {
            return new ResponseEntity<>(utilService.createQuery(dbQuery, database), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}

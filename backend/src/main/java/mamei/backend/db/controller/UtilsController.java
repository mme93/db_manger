package mamei.backend.db.controller;


import mamei.backend.db.model.DBQuery;
import mamei.backend.db.service.UtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/general")
public class UtilsController {

    private final UtilService utilService;

    public UtilsController(UtilService utilService) {
        this.utilService = utilService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String>getPing(){
        return new ResponseEntity<>("ping", HttpStatus.OK);
    }

    @PostMapping("/createQuery")
    public ResponseEntity<List<String>> createQuery(@RequestBody DBQuery dbQuery){
        try {
            return new ResponseEntity<>(utilService.createQuery(dbQuery), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/createQuery/{database}")
    public ResponseEntity<List<String>> createQueryWithDB(@RequestBody DBQuery dbQuery, @PathVariable String database){
        try {
            return new ResponseEntity<>(utilService.createQuery(dbQuery,database), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}

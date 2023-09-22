package mamei.backend.db.controller;

import mamei.backend.db.assets.DBSettingsConstants;
import mamei.backend.db.service.DBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@RequestMapping("/general")
public class DBController {


    private final DBService dbService;

    public DBController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        try {
            //String url = "jdbc:mysql://217.160.26.246:3306/mameie";
            String url = "jdbc:mysql://localhost:3306/mameie";
            String username = "admin";
            String password = "!Mameie93";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(DBSettingsConstants.showDatabases);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                System.out.println(databaseName);
            }
            return new ResponseEntity<>(dbService.getDatabaseNames(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/databases")
    public ResponseEntity<String> getAllDatabasesFromIP() {
        try {
            return new ResponseEntity<>(dbService.getDatabaseNames(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}

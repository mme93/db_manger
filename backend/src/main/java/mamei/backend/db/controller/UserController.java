package mamei.backend.db.controller;

import mamei.backend.db.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * All about MariaDB-User Information {@link Object}
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllUser() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/privileges/{user}")
    public ResponseEntity<List<String>> getPrivilegesFromUser(@PathVariable String user) {
        try {
            return new ResponseEntity<>(userService.getPrivilegesFromUser(user), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/privileges/update")
    public void updatePrivileges(){

    }

    @PutMapping("/create")
    public void createUser(){

    }

    @PutMapping("/delete")
    public void deleteUser(){

    }


}

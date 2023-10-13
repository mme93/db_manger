package mamei.backend.db.mariadb.user.controller;

import mamei.backend.db.mariadb.user.service.UserService;
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

    @GetMapping("/all/{serverName}")
    public ResponseEntity<String> getAllUser(@PathVariable String serverName) {
        try {
            return new ResponseEntity<>(userService.getUsers(serverName), HttpStatus.OK);
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
        userService.updatePrivileges();
    }

    @PutMapping("/create")
    public void createUser(){
        userService.createUser();
    }

    @PutMapping("/delete")
    public void deleteUser(){
        userService.deleteUser();
    }

    @PutMapping("/password")
    public void changePassword(){
        userService.changePassword();
    }

}

package mamei.backend.db.mariadb.helper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ping")
public class PingController {


    @GetMapping("/")
    public ResponseEntity<String> getPing() {
        return new ResponseEntity<>("ping", HttpStatus.OK);
    }


}

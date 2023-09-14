package mamei.backend.db.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/status")
@RestController
public class DBStatusController {


    @GetMapping("/ping")
    public ResponseEntity<String>getPing(){
        return new ResponseEntity<>("ping", HttpStatus.OK);
    }

}

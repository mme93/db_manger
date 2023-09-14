package mamei.backend.db.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/status")
public class DBStatusController {


    @GetMapping("/ping")
    public ResponseEntity<String>getPing(){
        return new ResponseEntity<>("ping", HttpStatus.OK);
    }

}

package mamei.backend.db.mariadb.html.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/overview")
public class HTMLOverviewController {

    @GetMapping("/{serverName}/databases")
    public List<String> getDatabase(){
        List<String>databases= new ArrayList<>();
        databases.add("car");
        databases.add("people");
        databases.add("apple");
        return databases;
    }
}

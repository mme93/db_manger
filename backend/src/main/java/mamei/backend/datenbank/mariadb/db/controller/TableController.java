package mamei.backend.datenbank.mariadb.db.controller;

import mamei.backend.datenbank.mariadb.db.model.table.TableColumn;
import mamei.backend.datenbank.mariadb.db.model.table.TableMetaColumn;
import mamei.backend.datenbank.mariadb.db.model.table.TableMetaRow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("restTable")
public class TableController {

    @PostMapping("/validate")
    public ResponseEntity<String> validateTable(@RequestBody List<TableColumn>tableMetaColumnList){
        System.out.println(tableMetaColumnList.size());
        return new ResponseEntity<>("Validate", HttpStatus.OK);
    }

}

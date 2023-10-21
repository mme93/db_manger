package mamei.backend.db.mariadb.html.controller;

import mamei.backend.db.mariadb.html.model.DataBaseObj;
import mamei.backend.db.mariadb.html.model.ServerNameObj;
import mamei.backend.db.mariadb.server.service.ServerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HTMLIndexController {

    private final ServerService serverService;

    public HTMLIndexController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("serverList", serverService.getServerNameList());
        return "html/indexPage";
    }

    @PostMapping("/tables")
    public List<DataBaseObj> createDatabaseList(String serverName){

        return null;
    }

}

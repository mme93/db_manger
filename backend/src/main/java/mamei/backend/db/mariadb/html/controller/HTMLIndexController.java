package mamei.backend.db.mariadb.html.controller;

import mamei.backend.db.mariadb.server.service.ServerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/home")
public class HTMLIndexController {

    private final ServerService serverService;

    public HTMLIndexController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/overview")
    public String overviewPage(Model model) {
        model.addAttribute("serverNameList", serverService.getServerNames());
        return "html/overviewPage";
    }

}

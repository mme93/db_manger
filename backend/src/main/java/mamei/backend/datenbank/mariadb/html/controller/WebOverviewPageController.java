package mamei.backend.datenbank.mariadb.html.controller;


import mamei.backend.datenbank.mariadb.html.service.OverviewPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebOverviewPageController {

    private final OverviewPageService overviewPageService;

    public WebOverviewPageController(OverviewPageService overviewPageService) {
        this.overviewPageService = overviewPageService;
    }

    @GetMapping("/overview")
    public String overviewPage(Model model) {
        model.addAttribute("serverNameList", overviewPageService.getServerNameList());
        return "html/overviewPage";
    }

}

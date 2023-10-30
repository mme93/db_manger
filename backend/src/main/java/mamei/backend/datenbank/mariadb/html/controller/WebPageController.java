package mamei.backend.datenbank.mariadb.html.controller;


import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.html.service.OverviewPageService;
import mamei.backend.datenbank.mariadb.db.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/web")
public class WebPageController {

    private final OverviewPageService overviewPageService;
    private final TableService tableService;

    public WebPageController(OverviewPageService overviewPageService, TableService tableService) {
        this.overviewPageService = overviewPageService;
        this.tableService = tableService;
    }

    @GetMapping("/table/{tableName}")
    public String getTableContext(Model model, @PathVariable String tableName) {
        model.addAttribute("tableName", tableName);
        return "html/tablePage";
    }

    @GetMapping("/overview")
    public String overviewPage(Model model) {
        model.addAttribute("serverNameList", overviewPageService.getServerNameList());
        return "html/overviewPage";
    }

    @GetMapping("/tables/{serverName}/{databaseName}")
    public String getDatabaseOverview(@PathVariable String databaseName, @PathVariable String serverName, Model model) throws SQLException {

        model.addAttribute("serverName", serverName);
        model.addAttribute("databaseName", databaseName);
        model.addAttribute("tableNames", tableService.getTableNamesFromDatabase(new DatabaseServer(serverName,databaseName,null)));

        return "html/tableOverviewPage";
    }

}

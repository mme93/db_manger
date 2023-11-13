package mamei.backend.datenbank.mariadb.html.controller;


import mamei.backend.datenbank.mariadb.db.model.DatabaseServer;
import mamei.backend.datenbank.mariadb.db.service.DatabaseService;
import mamei.backend.datenbank.mariadb.html.service.OverviewPageService;
import mamei.backend.datenbank.mariadb.db.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/web")
public class WebPageController {

    private final OverviewPageService overviewPageService;
    private final TableService tableService;
    private final DatabaseService databaseService;

    public WebPageController(OverviewPageService overviewPageService, TableService tableService, DatabaseService databaseService) {
        this.overviewPageService = overviewPageService;
        this.tableService = tableService;
        this.databaseService = databaseService;
    }

    @GetMapping("/table/{serverName}/{databaseName}/{tableName}")
    public String getTableContext(Model model, @PathVariable String databaseName, @PathVariable String serverName, @PathVariable String tableName) throws SQLException {
        model.addAttribute("tableName", tableName);
        model.addAttribute("tableView", tableService.getTableContext(new DatabaseServer(serverName, databaseName, tableName)));
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
        model.addAttribute("tableNames", tableService.getTableNamesFromDatabase(new DatabaseServer(serverName, databaseName, null)));
        return "html/tableOverviewPage";
    }

    @GetMapping("/createTablePage/{serverName}/{databaseName}")
    public String getCreateTable(@PathVariable String databaseName, @PathVariable String serverName, Model model) throws SQLException {
        model.addAttribute("serverName", serverName);
        model.addAttribute("databaseName", databaseName);
        model.addAttribute("title", "Create Table in Database: " + databaseName);
        return "html/createTablePage";
    }

    @GetMapping("/database/{serverName}")
    public String getDatabaseOverviewPage(Model model, @PathVariable String serverName) throws SQLException {
        model.addAttribute("serverName",serverName);
        model.addAttribute("databaseNameList",databaseService.getDatabaseNameByServer(serverName));
        return "html/databaseOverviewPage";
    }

}

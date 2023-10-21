package mamei.backend.db.mariadb.html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HTMLLoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(){
        return "html/loginPage";
    }
}

package cf.zhul.scanqrcodetologin.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String logioPage() {
        return "login";
    }
}

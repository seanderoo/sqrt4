package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String inlog() {
        return "login";
    }

//    @PostMapping("/login")
//    public String inlogError() {
//        return "login";
//    }
}

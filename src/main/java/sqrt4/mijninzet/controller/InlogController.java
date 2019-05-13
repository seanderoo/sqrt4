package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.repository.UserRepository;
import sqrt4.mijninzet.model.users.User;

@Controller
public class InlogController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login";
    }

}

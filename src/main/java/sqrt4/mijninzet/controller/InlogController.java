package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sqrt4.mijninzet.repository.UserRepository;
import sqrt4.mijninzet.model.users.User;

@Controller
public class InlogController extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository repo;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/index");
    }

    @GetMapping("/inlog")
    public String inlog() {
        return "inlog";
    }
    @GetMapping("/test")
    public String test() {
        User user = repo.getOne(1); //code om database te intialiseren kan later weg
        return "test";
    }
}

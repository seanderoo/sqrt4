package sqrt4.mijninzet.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InlogController extends WebSecurityConfigurerAdapter {

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
        return "test";
    }
}

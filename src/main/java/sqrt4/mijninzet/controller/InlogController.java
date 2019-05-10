package sqrt4.mijninzet.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    private SessionFactory sessionFactory;

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
    //deze merthode geeft een boolean terug die je dan weer kunt gebruiken in de methode 'inlog' om een inloggen-gelukt
    // of een inloggen-mislukt pagina te laten zien.
    public boolean checkLogin(User user) {
        Session session = sessionFactory.openSession();
        boolean inlogCorrect = false;
        return inlogCorrect;
    }
}

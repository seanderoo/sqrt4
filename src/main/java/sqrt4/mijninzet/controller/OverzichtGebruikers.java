package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.UserRepository;

import java.util.List;

@Controller
public class OverzichtGebruikers extends AbstractController{

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/overzicht-gebruikers")
    public String overzichtGebruikers(Model model) {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        model.addAttribute("gebruikers", users);
        return "overzicht-gebruikers";
    }
}

package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Role;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.RoleRepository;
import sqrt4.mijninzet.repository.UserRepository;

import java.util.List;

@Controller
public class GebruikerController extends AbstractController{

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/admin/overzicht-gebruikers")
    public String overzichtGebruikers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("gebruikers", users);
        return "overzicht-gebruikers";
    }

    @PostMapping("/admin/overzicht-gebruikers")
    public String verwijderGebruiker(@RequestParam("Verwijder") Long userId, Model model) {
        User activeUser = voegActiveUserToe();
        if(userId == activeUser.getId()){
            List<User> users = userRepository.findAll();
            model.addAttribute("gebruikers", users);
            return "overzicht-gebruikers";
        }
        userRepository.deleteById(userId);
        List<User> users = userRepository.findAll();
        model.addAttribute("gebruikers", users);
        return "overzicht-gebruikers";
    }

    @GetMapping("/admin/nieuwe-gebruiker")
    public String nieuweGebruiker(Model model) {
        List<Role> rollen = roleRepository.findAll();
        model.addAttribute("roles", rollen);
        return "/nieuwe-gebruiker";
    }

    @PostMapping("/admin/nieuwe-gebruiker")
    public String nieuweGebruiker(@ModelAttribute("user") User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setRoles(user.getRoles().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
        return "/gebruiker-toegevoegd";
    }
}

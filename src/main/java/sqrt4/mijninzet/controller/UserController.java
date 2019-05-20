package sqrt4.mijninzet.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.model.Role;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.RoleRepository;
import sqrt4.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/nieuwe-gebruiker")
    //kun je mbv @RequestParam de gekozen rol uitlezen? (Karin)
    public String nieuweGebruiker(Model model) {
        List<Role> rollen = roleRepository.findAll();
        model.addAttribute("roles", rollen);
        User user = new User("", "", "", "", "", "");
        model.addAttribute("user", user);
        return "nieuwe-gebruiker";
    }

    @PostMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(@ModelAttribute("user") User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setRoles(user.getRoles().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println(user);
        return "gebruiker-toegevoegd";
    }


}

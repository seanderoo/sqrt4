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
public class NieuweGebruikerController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(Model model) {
        List<Role> rollen = roleRepository.findAll();
        model.addAttribute("roles", rollen);
        return "nieuwe-gebruiker";
    }

    @PostMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(@ModelAttribute("user") User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setRoles(user.getRoles().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
        return "gebruiker-toegevoegd";
    }


}

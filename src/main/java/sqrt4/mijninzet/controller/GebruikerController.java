package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Role;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.RoleRepository;
import sqrt4.mijninzet.repository.UserRepository;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class GebruikerController extends AbstractController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    private final int ACTIVE = 1;

    @GetMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(Model model) {
        List<Role> rollen = roleRepository.findAll();
        model.addAttribute("roles", rollen);
        return "admin/nieuwe-gebruiker";
    }

    @PostMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(User user, Model model) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setRoles(user.getRoles().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(ACTIVE);
        userRepository.save(user);
        List<User> users = gebruikersLijstZonder("No");
        model.addAttribute("gebruikers", users);
        return "admin/overzicht-gebruikers";
    }

    @GetMapping("/overzicht-gebruikers")
    public String overzichtGebruikers(Model model) {
        List<User> users = gebruikersLijstZonder("No");
        model.addAttribute("gebruikers", users);
        return "admin/overzicht-gebruikers";
    }

    @PostMapping("/overzicht-gebruikers")
    public String verwijderGebruiker(@RequestParam(value = "Verwijder", required = false) Long userIdVerwijder,
                                     @RequestParam(value = "Wijzig", required = false) Long userIdWijzig,
                                     Model model) {
        User activeUser = voegActiveUserToe();
        if (userIdVerwijder != null) {
            if (userIdVerwijder == activeUser.getId()) {
                List<User> users = gebruikersLijstZonder("No");
                model.addAttribute("gebruikers", users);
                return "/admin/overzicht-gebruikers";
            } else if (userIdVerwijder != activeUser.getId()) {
                userRepository.deleteById(userIdVerwijder);
                List<User> users = gebruikersLijstZonder("No");
                model.addAttribute("gebruikers", users);
                return "admin/overzicht-gebruikers";
            }
        } else if (userIdWijzig != null) {
            User user = userRepository.findUserById(userIdWijzig);
            model.addAttribute("gebruiker", user);

            String gebruikerrollen = user.getRoles();
            model.addAttribute("gebruikerrollen", gebruikerrollen);

            List<Role> rollen = roleRepository.findAll();
            model.addAttribute("roles", rollen);
            String rollenString = zetOmNaarString(rollen);
            model.addAttribute("alleRollen", rollenString);

            return "admin/wijzig-gebruiker";
        }
        return "admin/overzicht-gebruikers";
    }


    private String zetOmNaarString(List<Role> rollen) {
        StringBuilder bob = new StringBuilder();
        for (int i = 0; i < rollen.size(); i++) {
            bob.append(rollen.get(i).getName().toUpperCase());
            if (i != (rollen.size()-1)) {
                bob.append(",");
            }
        }
        String string = bob.toString();
        return string;
    }
}

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

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class GebruikerController extends AbstractController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(Model model) {
        List<Role> rollen = roleRepository.findAll();
        model.addAttribute("roles", rollen);
        return "admin/nieuwe-gebruiker";
    }

    @PostMapping("/nieuwe-gebruiker")
    public String nieuweGebruiker(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setRoles(user.getRoles().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
        return "admin/gebruiker-toegevoegd";
    }

    @GetMapping("/overzicht-gebruikers")
    public String overzichtGebruikers(Model model) {
        List<User> users = userRepository.findAll();
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
                List<User> users = userRepository.findAll();
                model.addAttribute("gebruikers", users);
                return "/admin/overzicht-gebruikers";
            } else if (userIdVerwijder != activeUser.getId()) {
                userRepository.deleteById(userIdVerwijder);
                List<User> users = userRepository.findAll();
                model.addAttribute("gebruikers", users);
                return "admin/overzicht-gebruikers";
            }
        } else if (userIdWijzig != null) {
            User user = userRepository.findUserById(userIdWijzig);
            model.addAttribute("gebruiker", user);

            String gebruikerrol = user.getRoles();
            gebruikerrol = hoofdletterPlusKleineLetters(gebruikerrol);
            model.addAttribute("gebruikerrol", gebruikerrol);

            List<Role> rollen = roleRepository.findAll();
            rollen = verwijderGebruikerrolUitLijst(rollen, user);
            model.addAttribute("roles", rollen);

            return "admin/wijzig-gebruiker";
        }
        return "admin/overzicht-gebruikers";
    }


    @PostMapping("/wijzig-gebruiker")
    public String wijzigGebruiker(User user, Model model) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setRoles(user.getRoles().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        model.addAttribute("gebruikers", users);
        return "admin/overzicht-gebruikers";
    }

    private List<Role> verwijderGebruikerrolUitLijst(List<Role> lijstMetRollen, User gebruiker) {
        List<Role> lijstZonderGebruikerrol = new ArrayList<>();
        String gebruikerrol = gebruiker.getRoles();
        gebruikerrol = hoofdletterPlusKleineLetters(gebruikerrol);
        for (Role rol : lijstMetRollen) {
            if (!rol.getName().equals(gebruikerrol)) {
                lijstZonderGebruikerrol.add(rol);
            }
        }
        System.out.println(lijstZonderGebruikerrol);
        return lijstZonderGebruikerrol;
    }

    private String hoofdletterPlusKleineLetters(String woord) {
        woord = woord.substring(0,1).toUpperCase() + woord.substring(1).toLowerCase();
        return woord;
    }
}

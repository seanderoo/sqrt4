package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.UserRepository;

@Controller
public class ProfielController extends AbstractController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profiel")
    public String profiel(Model model) {
        User user = voegActiveUserToe();
        model.addAttribute("user", user);
        return "profiel";
    }

    @PostMapping("/profiel")
    public String wijzigGebruiker(User user, Model model) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUsername(voegActiveUserToe().getUsername());
        user.setFirstName(voegActiveUserToe().getFirstName());
        user.setLastName(voegActiveUserToe().getLastName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(voegActiveUserToe().getRoles());
        user.setProfilePic("");
        user.setActive(1);
        userRepository.save(user);
        model.addAttribute("user", user);
        return "home";
    }
}


package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import sqrt4.mijninzet.config.UserPrincipal;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.UserRepository;


@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public String Home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        System.out.println(userName);
        User activeUser = userRepository.findByUsername(userName);
        model.addAttribute("user", activeUser);
        System.out.println(activeUser);
        return "home";
    }
}

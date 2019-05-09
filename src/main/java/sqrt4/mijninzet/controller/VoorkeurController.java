package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class VoorkeurController {

    @GetMapping("/voorkeuren")
    public String Voorkeur() {
        return "voorkeuren";
    }
}


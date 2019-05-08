package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class VoorkeurController {

    @GetMapping("/Voorkeuren")
    public String Voorkeur(HttpServletResponse response,  Model model) {
        response.addHeader("Voorbeeld1", "Voorbeeld2");
//        model.addAttribute("voorkeuren", voorkeuren);
        return "Voorkeuren";
    }
}

//    String voorkeuren,

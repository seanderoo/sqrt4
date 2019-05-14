package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class VoorkeurController {

    @GetMapping("/voorkeuren")
    public String geefVoorkeurOp( @RequestParam(value = "vak", required = false) String vakNaam, Model model) {
        model.addAttribute("vak", vakNaam);
        return "voorkeuren";
    }

    @PostMapping("/voorkeuren")
    public String voorkeurToegevoegd() {
        return "voorkeuren-toegevoegd";
    }


}


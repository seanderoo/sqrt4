package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Voorkeur;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.VoorkeurenRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VoorkeurController {

    @Autowired
    VakRepository repository;
    @Autowired
    VoorkeurenRepository voorkeurenRepository;

    ArrayList<Voorkeur> voorkeurs = new ArrayList<>();

    @GetMapping("/voorkeuren")
    public String getVakken(Model model) {
        List<Vak> vakken = repository.findAll();
        model.addAttribute("vakken", vakken);
        return "voorkeuren";
    }

    @PostMapping("/voorkeuren")
    public String voorkeurToegevoegd(@ModelAttribute("voorkeur") Voorkeur voorkeur) {
        voorkeurenRepository.save(voorkeur);
        System.out.println(voorkeur);
        return "voorkeuren-toegevoegd";
    }
}


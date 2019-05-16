package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.tags.form.RadioButtonTag;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Voorkeur;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.VoorkeurenRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VoorkeurController {

    @Autowired
    VakRepository repository;
    @Autowired
    VoorkeurenRepository voorkeurenRepository;

    @GetMapping("/voorkeuren")
    public String getVakken(Model model) {
        List<Vak> vakken = repository.findAll();
        model.addAttribute("vakken", vakken);
        return "voorkeuren";
    }

    @PostMapping("/voorkeuren")
        public String voorkeurToegevoegd(@ModelAttribute("voorkeur") Voorkeur voorkeur, Vak vak, User user, Model model) {
        model.addAttribute("voorkeurId", voorkeur.getVoorkeurId());
        model.addAttribute("user", user.getId());
        model.addAttribute("vak", vak.getVakId());
        model.addAttribute("voorkeur", voorkeur.getVoorkeur());
        System.out.println("Vak: " + vak.getVakId());
        System.out.println("User: " + user.getId());
        System.out.println("Voorkeur: " + voorkeur.getVoorkeur());
        voorkeurenRepository.save(voorkeur);
        return "home";
    }
}


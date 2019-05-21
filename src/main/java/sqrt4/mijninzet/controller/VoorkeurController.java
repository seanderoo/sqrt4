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
import java.util.List;
import java.util.Map;

@Controller
public class VoorkeurController {

    @Autowired
    private VakRepository repository;
    @Autowired
    private VoorkeurenRepository voorkeurenRepository;

    @GetMapping("/voorkeuren")
    public String getVakken(Model model) {
        List<Vak> vakken = repository.findAll();
        Voorkeur voorkeur = new Voorkeur();
        model.addAttribute("voorkeur", voorkeur);
        model.addAttribute("vakken", vakken);
        return ("voorkeuren");
    }

    @PostMapping("/voorkeuren")
    public String voorkeurToegevoegd(@RequestParam Map<String, String> allParams,
                                     @ModelAttribute("voorkeur") Voorkeur voorkeur) {
        List<Vak> vakken = repository.findAll();

        for (Vak vak: vakken) {
            String key = null;
            for (String k : allParams.keySet()) {
                int sleutel = Integer.parseInt(k);
                System.out.println("is dit mijn value? " + allParams.get(k));
                if (vak.getVakId() == sleutel) {
                    key = k;
                }
            }
            Voorkeur voorkeur1 = new Voorkeur();
            voorkeur1.setVak(vak);
            voorkeur1.setVoorkeur(allParams.get(key));
            voorkeurenRepository.save(voorkeur1);
        }
        System.out.println(allParams.entrySet());
        return ("voorkeuren-toegevoegd");
    }
}


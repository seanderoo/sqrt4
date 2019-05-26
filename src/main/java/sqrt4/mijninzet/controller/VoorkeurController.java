package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Voorkeur;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.VoorkeurenRepository;
import java.util.List;
import java.util.Map;

@Controller
public class VoorkeurController extends AbstractController {

    @Autowired
    private VakRepository vakRepository;
    @Autowired
    private VoorkeurenRepository voorkeurenRepository;

    @GetMapping("/voorkeuren")
    public String getVakken(Model model) {
        List<Vak> vakkenLijst = vakRepository.findAll();
        Voorkeur voorkeur = new Voorkeur();
        model.addAttribute("vakkenLijst", vakkenLijst);
        model.addAttribute("voorkeur", voorkeur);
        return "voorkeuren";
    }

    @PostMapping("/voorkeuren")
    public String bewaarVoorkeur(@ModelAttribute("voorkeur") Voorkeur voorkeur, Vak vak) {
        Voorkeur ingevuldeVoorkeur = new Voorkeur();
        ingevuldeVoorkeur.setUser(voegActiveUserToe());
        ingevuldeVoorkeur.setVak(vak);
        ingevuldeVoorkeur.setVoorkeurGebruiker(voorkeur.getVoorkeurGebruiker());
        voorkeurenRepository.deleteByVak_VakIdAndUser(vak.getVakId(), voegActiveUserToe());
        voorkeurenRepository.save(ingevuldeVoorkeur);
        return "voorkeuren";
    }
}

//    List<Vak> vakken = vakRepository.findAll(); // pas aan voorkeurRepo
//
//        for (Vak vak: vakken) {
//                String key = null;
//                for (String k : allParams.keySet()) {
//                int sleutel = Integer.parseInt(k);
//                if (vak.getVakId() == sleutel) {
//                key = k;
//                }
//                }
//                Voorkeur voorkeur1 = new Voorkeur();
//                voorkeur1.setVak(vak);
//                voorkeur1.setVoorkeur(allParams.get(key));
//                voorkeur1.setUser(voegActiveUserToe());
//
//                if (allParams.get(key) != null) {
//                voorkeurenRepository.deleteByVak_VakIdAndUser(vak.getVakId(), voegActiveUserToe());
//                voorkeurenRepository.save(voorkeur1);
//                }
//                }
//                return "voorkeuren";

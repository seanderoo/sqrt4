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
    public String getVakken(Model model, Vak vak) {
        List<Vak> vakkenLijst = vakRepository.findAll();
        Voorkeur voorkeur = new Voorkeur();


        Voorkeur databaseVoorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vakkenLijst.get(0), voegActiveUserToe());


        if (databaseVoorkeur == null) {
            model.addAttribute("voorkeur", voorkeur);
        } else {
            model.addAttribute("voorkeur", databaseVoorkeur);
        }

        model.addAttribute("vakkenLijst", vakkenLijst);

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

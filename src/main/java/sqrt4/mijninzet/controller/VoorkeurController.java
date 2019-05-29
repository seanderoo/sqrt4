package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Voorkeur;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.VoorkeurenRepository;
import java.util.List;

@Controller
public class VoorkeurController extends AbstractController {

    @Autowired
    private VakRepository vakRepository;
    @Autowired
    private VoorkeurenRepository voorkeurenRepository;

    @GetMapping("/voorkeuren")
    public String getVakken(Model model) {
        List<Vak> vakkenLijst = vakRepository.findAll();
        User user = userRepo.findByUsername(voegActiveUserToe().getUsername());
        List<Voorkeur> voorkeurLijst = voorkeurenRepository.findAllByUser(voegActiveUserToe());
        System.out.println(voorkeurLijst);


        Voorkeur voorkeur = new Voorkeur();

        Voorkeur defaultVoorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vakkenLijst.get(0), voegActiveUserToe());

        if (defaultVoorkeur == null) {
            model.addAttribute("voorkeur", voorkeur);
        } else {
            model.addAttribute("voorkeur", defaultVoorkeur);
        }

        model.addAttribute("vakkenLijst", vakkenLijst);
        model.addAttribute("user", user);
        return "voorkeuren";
    }

    @PostMapping("/voorkeuren")
    public String bewaarVoorkeur(@ModelAttribute("voorkeur") Voorkeur voorkeur, Vak vak, Model model) {
        Voorkeur ingevuldeVoorkeur = new Voorkeur();
        ingevuldeVoorkeur.setUser(voegActiveUserToe());
        ingevuldeVoorkeur.setVak(vak);
        ingevuldeVoorkeur.setVoorkeurGebruiker(voorkeur.getVoorkeurGebruiker());
        voorkeurenRepository.deleteByVak_VakIdAndUser(vak.getVakId(), voegActiveUserToe());
        voorkeurenRepository.save(ingevuldeVoorkeur);

        List<Vak> vakkenLijst = vakRepository.findAll();
        model.addAttribute("vakkenLijst", vakkenLijst);
//        model.addAttribute("voorkeur", ingevuldeVoorkeur);
        return "voorkeuren";
    }
}



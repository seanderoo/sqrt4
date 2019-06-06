package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/docent/voorkeuren")
    public String getVakken(Model model) {
        List<Vak> vakkenLijst = vakRepository.findAll();
        User user = voegActiveUserToe();
        List<Voorkeur> voorkeurLijst = voorkeurenRepository.findAllByUser(voegActiveUserToe());
        System.out.println(voorkeurLijst);

        Voorkeur voorkeur = new Voorkeur();

        Voorkeur defaultVoorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vakkenLijst.get(0), voegActiveUserToe());

        List<Voorkeur> voorkeurList = voorkeurenRepository.findAllByVakOrderByVoorkeurGebruikerDesc(vakkenLijst.get(0));
        for (Voorkeur voorkeur1: voorkeurList) {
            System.out.println("Opgegeven voorkeur: " + voorkeur1.getVoorkeurGebruiker() + ", Docent: " +voorkeur1.getUser().getUsername());
        }

        if (defaultVoorkeur == null) {
            model.addAttribute("voorkeur", voorkeur);
        } else {
            model.addAttribute("voorkeur", defaultVoorkeur);
        }

        model.addAttribute("vakkenLijst", vakkenLijst);
        model.addAttribute("user", user);
        return "voorkeuren";
    }

    @PostMapping("/docent/voorkeuren")
    public String bewaarVoorkeur(@ModelAttribute("voorkeur") Voorkeur voorkeur, Vak vak, Model model) {
        Voorkeur ingevuldeVoorkeur = new Voorkeur();
        ingevuldeVoorkeur.setUser(voegActiveUserToe());
        ingevuldeVoorkeur.setVak(vak);
        ingevuldeVoorkeur.setVoorkeurGebruiker(voorkeur.getVoorkeurGebruiker());
        voorkeurenRepository.deleteByVak_VakIdAndUser(vak.getVakId(), voegActiveUserToe());
        voorkeurenRepository.save(ingevuldeVoorkeur);

        List<Vak> vakkenLijst = vakRepository.findAll();
        model.addAttribute("vakkenLijst", vakkenLijst);
        model.addAttribute("user", voegActiveUserToe());
        return "voorkeuren";
    }
}
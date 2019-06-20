package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.model.Sollicitatie;
import sqrt4.mijninzet.model.Vacature;
import sqrt4.mijninzet.repository.SollicitatieRepository;
import sqrt4.mijninzet.repository.VacatureRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class SolliciterenController extends AbstractController{

    @Autowired
    VacatureRepository vacrepo;
    @Autowired
    SollicitatieRepository solrepo;

    @GetMapping("/docent/solliciteren")
    public String getVacatures(Model model) {
        model.addAttribute("vacatures", welNietGesolliciteerd("vacatures"));
        return "docent/vacature-overzicht";
    }

    @GetMapping("/docent/sollicitaties-details-{overzicht}")
    public String sollicitatieDetails(@ModelAttribute Vacature vacature,
                                      @PathVariable String overzicht,
                                      Model model) {
        Vacature gekozenVacature = vacrepo.findByVacatureNaam(vacature.getVacatureNaam());
        model.addAttribute("vacature", gekozenVacature);
        model.addAttribute("soort", overzicht);
        return "docent/sollicitaties-details";
    }

    @GetMapping("/docent/sollicitaties")
    public String alleSollicitaties(@ModelAttribute("sollicitatie") Vacature vacatureId, Model model) {
        Vacature vacature = vacrepo.findById(vacatureId.getId());
        Sollicitatie sollicitatie;
        if (solrepo.findByUserAndVacature(voegActiveUserToe(), vacature) == null) {
            sollicitatie = new Sollicitatie(voegActiveUserToe(), vacature);
            solrepo.save(sollicitatie);
        }
        else {
            sollicitatie = solrepo.findByUserAndVacature(voegActiveUserToe(), vacature);
            solrepo.delete(sollicitatie);
        }
        model.addAttribute("sollicitaties", solrepo.findAllByUser(voegActiveUserToe()));
        return "docent/sollicitaties-overzicht";
    }

    @GetMapping("/docent/sollicitaties-overzicht")
    public String getSollicitaties(Model model) {
        List<Sollicitatie> sollicitaties = solrepo.findAllByUser(voegActiveUserToe());
        Collections.sort(sollicitaties);
        model.addAttribute("sollicitaties", sollicitaties);
        return "docent/sollicitaties-overzicht";
    }

    @GetMapping("/coordinator/overzicht-sollicitaties")
    public String coordinatorGetSollicitaties(Model model) {
        List<Sollicitatie> sollicitaties = solrepo.findAll();
        Collections.sort(sollicitaties);
        model.addAttribute("sollicitaties", sollicitaties);
        Sollicitatie.Status[] enums = Sollicitatie.Status.values();
        model.addAttribute("statussen", enums);
        return "coordinator/overzicht-sollicitaties";
    }
    @PostMapping("/coordinator/overzicht-sollicitaties")
    public String coordinatorGetMeerSollicitaties(@ModelAttribute("sollicitatie") Sollicitatie sol,
                                                  Model model) {
        solrepo.save(sol);
        List<Sollicitatie> sollicitaties = solrepo.findAll();
        Collections.sort(sollicitaties);
        model.addAttribute("sollicitaties", sollicitaties);
        Sollicitatie.Status[] enums = Sollicitatie.Status.values();
        model.addAttribute("statussen", enums);
        return "coordinator/overzicht-sollicitaties";
    }

    //lijst van vacatures waar huidige gebruiker al wel of nog niet op gesolliciteerd heeft. Input bepaalt welke lijst
    //returned wordt (Karin)
    public List<Vacature> welNietGesolliciteerd(String keuze) {
        List<Vacature> gesolliciteerdeVacatures = new ArrayList<>();
        List<Vacature> openVacatures = vacrepo.findAll();
        List<Sollicitatie> sollicitaties = solrepo.findAll();
        long userId = voegActiveUserToe().getId();
        for (Sollicitatie sol: sollicitaties) {
            if (userId == sol.getUser().getId()) {
                openVacatures.remove(sol.getVacature());
                gesolliciteerdeVacatures.add(sol.getVacature());
            }
        }
        switch (keuze) {
            case "vacatures":
                Collections.sort(openVacatures);
                return openVacatures;
            case "sollicitaties":
                Collections.sort(gesolliciteerdeVacatures);
                return gesolliciteerdeVacatures;
        }
        return null;
    }
}

package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.VakRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class VakController extends AbstractController{

    @Autowired
    VakRepository vakRepository;

    @GetMapping("/coordinator/vak-aanmaken")
    public String vakAanmaken() {
        return "coordinator/vak-aanmaken";
    }

    @PostMapping("/coordinator/vak-aanmaken")
    public String vakToegevoegd(@ModelAttribute Vak vak,
                                Model model){
        vakRepository.save(vak);
        List<Vak> vakken = vakkenLijstZonder("Geen les");
        model.addAttribute("vakken", vakken);
        return "coordinator/vak-overzicht";
    }
    @GetMapping("/coordinator/vak-overzicht")
    public String vakOverzicht(Model model) {
        List<Vak> vakken = vakkenLijstZonder("Geen les");
        model.addAttribute("vakken", vakken);
        return "coordinator/vak-overzicht";
    }
    //deze is voor het verwijderen of wijzigen van een vak. Id van dat vak moet meegegeven worden als requestparameter
    @PostMapping("/coordinator/vak-overzicht")
    public String nieuwVakOverzicht(@RequestParam(value = "Verwijder", required = false) Integer verwijderVakId,
                                    @RequestParam(value = "Wijzig", required = false) Integer wijzigVakId,
                                    Model model) {
        if (verwijderVakId != null) {
            Vak verwijderVak = vakRepository.findVakByVakId(verwijderVakId);
            vakRepository.delete(verwijderVak);
            List<Vak> vakken = vakkenLijstZonder("Geen les");
            model.addAttribute("vakken", vakken);
            return "coordinator/vak-overzicht";
        } else if (wijzigVakId != null) {
            Vak wijzigVak = vakRepository.findVakByVakId(wijzigVakId);
            model.addAttribute(wijzigVak);
            return "coordinator/vak-wijzigen";
        }
        return null;
    }

    @GetMapping("/coordinator/vak-wijzigen")
    public String wijzigVak(@RequestParam("Wijzig") int vakId,
                            Model model) {
        model.addAttribute(vakRepository.findById(vakId));
        return "coordinator/vak-wijzigen";
    }
    //deze is voor het wijzigen van het vak.
    @PostMapping("coordinator/vak-wijzigen")
    public String vakGewijzigd(Model model) {
        //vakrepo.save
        List<Vak> vakken = vakkenLijstZonder("Geen les");
        model.addAttribute("vakken", vakken);
        return "coordinator/vak-overzicht";
    }
}

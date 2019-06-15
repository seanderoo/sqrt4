package sqrt4.mijninzet.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import sqrt4.mijninzet.model.Sollicitatie;
        import sqrt4.mijninzet.model.Vacature;
        import sqrt4.mijninzet.repository.SollicitatieRepository;
        import sqrt4.mijninzet.repository.VacatureRepository;

        import java.util.ArrayList;
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
        model.addAttribute("sollicitaties", welNietGesolliciteerd("sollicitaties"));
        return "/docent/sollicitaties-overzicht";
    }

    @GetMapping("/docent/sollicitaties-overzicht")
    public String getSollicitaties(Model model) {
        model.addAttribute("sollicitaties", welNietGesolliciteerd("sollicitaties"));
        return "docent/sollicitaties-overzicht";
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
                return openVacatures;
            case "sollicitaties":
                return gesolliciteerdeVacatures;
        }
        return null;
    }
}

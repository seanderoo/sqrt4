package sqrt4.mijninzet.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import sqrt4.mijninzet.model.Sollicitatie;
        import sqrt4.mijninzet.model.User;
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

    @GetMapping("/solliciteren")
    public String getVacatures(Model model) {
        model.addAttribute("vacatures", welNietGesolliciteerd("vacatures"));
        return "solliciteren";
    }

    @GetMapping("/sollicitaties-details")
    public String sollicitatieDetails(@ModelAttribute("vacature") Vacature vacature, Model model) {
        Vacature gekozenVacature = vacrepo.findByVacatureNaam(vacature.getVacatureNaam());
        model.addAttribute("vacature", gekozenVacature);
        return "sollicitaties-details";
    }

    @GetMapping("/sollicitaties")
    public String alleSollicitaties(@ModelAttribute("sollicitatie") Vacature vacatureId, Model model) {
        Sollicitatie sollicitatie = new Sollicitatie(voegActiveUserToe(), vacrepo.findById(vacatureId.getId()));
        solrepo.save(sollicitatie);
        model.addAttribute("sollicitaties", welNietGesolliciteerd("sollicitaties"));
        return "sollicitaties-overzicht";
    }

    @GetMapping("/sollicitaties-overzicht")
    public String getSollicitaties(Model model) {
        model.addAttribute("sollicitaties", welNietGesolliciteerd("sollicitaties"));
        return "sollicitaties-overzicht";
    }

    //lijst van vacatures waar huidige gebruiker al wel of nog niet op gesolliciteerd heeft. Input bepaalt welke lijst
    //returned wordt
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

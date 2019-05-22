package sqrt4.mijninzet.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("vacatures", nogNietGesolliciteerd());
        Vacature testVacature = new Vacature();
        model.addAttribute(testVacature);
        return "solliciteren";
    }

    @GetMapping("/sollicitaties-details")
    public String sollicitatieDetails(@ModelAttribute("vacature") Vacature vacature, Model model) {
        Vacature gekozenVacature = vacrepo.findByVacatureNaam(vacature.getVacatureNaam());
        model.addAttribute("vacature", gekozenVacature);
        System.out.println(gekozenVacature);
        return "sollicitaties-details";
    }

    @GetMapping("/sollicitaties")
    public String alleSollicitaties(@ModelAttribute("sollicitatie") Vacature vacatureId, Model model) {
        Sollicitatie sollicitatie = new Sollicitatie(voegActiveUserToe(), vacrepo.findById(vacatureId.getId()));
        System.out.println(sollicitatie);
        solrepo.save(sollicitatie);
        System.out.println(vacatureId);
        return "sollicitaties-overzicht";
    }

    //lijst van vacatures waar huidige gebruiker nog niet op gesolliciteerd heeft
    public List<Vacature> nogNietGesolliciteerd() {
        List<Vacature> vacatures = vacrepo.findAll();
        List<Sollicitatie> sollicitaties = solrepo.findAll();
        long userId = voegActiveUserToe().getId();
        for (Sollicitatie sol: sollicitaties) {
            if (userId == sol.getUser().getId()) {
                vacatures.remove(sol.getVacature());
            }
        }
        return vacatures;
    }
}

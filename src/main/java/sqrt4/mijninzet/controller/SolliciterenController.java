package sqrt4.mijninzet.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import sqrt4.mijninzet.model.Vacature;
        import sqrt4.mijninzet.repository.VacatureRepository;

        import java.util.List;

@Controller
public class SolliciterenController {

    @Autowired
    VacatureRepository repository;

    @GetMapping("/solliciteren")
    public String getVacatures(Model model) {
        List<Vacature> vacatures = repository.findAll();
        model.addAttribute("vacatures", vacatures);
        return "solliciteren";
    }
    @PostMapping("/solliciteren")
    public String solliciteren() {
        return "solliciteren";
    }

    @PostMapping("/sollicitaties/opgeslagen")
    public String sollicitatiesOpgeslagen() {
        return "sollicitaties-opgeslagen";
    }
}

package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.repository.AlgemeneBeschikbaarheidRepository;

@RequestMapping("/admin")
@Controller
public class MaakCohortController extends AbstractController {

    @Autowired
    AlgemeneBeschikbaarheidRepository abRepo;

    @GetMapping("/maakcohort")
    public String maakCohort( Model model, Cohort cohort){
        return "maakcohort";
    }
    Cohort cohort = new Cohort();

    @PostMapping("/maakcohort")
    public String nieuwCohort(Model model,
                                @ModelAttribute("cohort") Cohort cohort,
                                @RequestParam("cohortNummer") int cohortNummer,
                                @RequestParam ("startJaar") int startJaar,
                                @RequestParam("startWeek") int startWeek,
                                @RequestParam ("eindWeek") int eindWeek){

        try {
            cohort = new Cohort(cohortNummer, startWeek, startJaar, eindWeek);
            cohort.setUser(voegActiveUserToe());
            abRepo.save(cohort);
            model.addAttribute("user", voegActiveUserToe());
        } catch (Exception e) {
            System.out.println("Something went wrong... " + e.getMessage());
        }

        model.addAttribute("user", voegActiveUserToe());

        return "home";
    }
}

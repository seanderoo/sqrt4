package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.repository.CohortRepository;

@Controller
public class MaakCohortController extends AbstractController {

    @Autowired
    CohortRepository cohortRepo;

    @GetMapping("/admin/maakcohort")
    public String maakCohort( Model model, Cohort cohort){
        return "/maakcohort";
    }
    Cohort cohort = new Cohort();

    @PostMapping("/admin/maakcohort")
    public String nieuwCohort(Model model,
                                @ModelAttribute("cohort") Cohort cohort,
                                @RequestParam("cohortNummer") int cohortNummer,
                                @RequestParam ("startJaar") int startJaar,
                                @RequestParam("startWeek") int startWeek,
                                @RequestParam ("eindWeek") int eindWeek){
        cohort = new Cohort(cohortNummer, startWeek, startJaar, eindWeek);
//        cohort.setUser(voegActiveUserToe());
        cohortRepo.save(cohort);
        model.addAttribute("user", voegActiveUserToe());
        return "/home";
    }
}

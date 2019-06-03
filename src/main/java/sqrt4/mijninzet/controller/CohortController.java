package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.repository.CohortRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class CohortController extends AbstractController {

    @Autowired
    CohortRepository cohortRepo;

    @GetMapping("/overzicht-cohort")
    public String overzichtCohort(Model model) {
        List<Cohort> cohorten = cohortRepo.findAll();
        Collections.sort(cohorten, new CohortComparator());
        model.addAttribute("cohortLijst", cohorten);
        return "/admin/overzicht-cohort";
    }

    @PostMapping("/verwijder-cohort")
    public String verwijderCohort(@RequestParam("Verwijder") int id, Model model) {
        cohortRepo.deleteById(id);
        List<Cohort> cohorten = cohortRepo.findAll();
        Collections.sort(cohorten, new CohortComparator());
        model.addAttribute("cohortLijst", cohorten);
        return "/admin/overzicht-cohort";
    }

    @GetMapping("/maakcohort")
    public String maakCohort(Model model) {
        return "/admin/maakcohort";
    }

    @PostMapping("/maakcohort")
    public String nieuwCohort(Model model,
                              @ModelAttribute("cohort") Cohort cohort,
                              @RequestParam("cohortNummer") int cohortNummer,
                              @RequestParam("startJaar") int startJaar,
                              @RequestParam("startWeek") int startWeek,
                              @RequestParam("eindWeek") int eindWeek) {

        try {
            cohort = new Cohort(cohortNummer, startWeek, startJaar, eindWeek);
//            cohort.setUser(voegActiveUserToe());
            cohortRepo.save(cohort);
            model.addAttribute("user", voegActiveUserToe());
        } catch (Exception e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return "/admin/overzicht-cohort";
        }
        model.addAttribute("user", voegActiveUserToe());
        List<Cohort> cohorten = cohortRepo.findAll();
        Collections.sort(cohorten, new CohortComparator());
        model.addAttribute("cohortLijst", cohorten);
        return "/admin/overzicht-cohort";
    }

    class CohortComparator implements Comparator<Cohort> {
        @Override
        public int compare(Cohort a, Cohort b) {
            if ( a.getStartJaar() > b.getStartJaar() ) {
                return 1;
            } else if ( a.getStartJaar() < b.getStartJaar() ) {
                return -1;
            } else {
                if ( a.getStartWeek() > b.getStartWeek() ) {
                    return 1;
                } else if ( a.getStartWeek() < b.getStartWeek() ) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}

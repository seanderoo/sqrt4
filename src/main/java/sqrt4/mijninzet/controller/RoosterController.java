package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.CohortRepository;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.WeekRepository;

import java.util.List;

@Controller
public class RoosterController extends AbstractController{

    @Autowired
    CohortRepository cohortRepo;
    @Autowired
    VakRepository vakRepo;
    @Autowired
    WeekRepository weekRepo;

    @GetMapping("/manager/rooster-maken")
    public String maakRooster(Model model) {
        List<Cohort> cohorten = cohortRepo.findAll();
        List<Vak> vakken = vakRepo.findAll();
        List<Week> weken = weekRepo.findAll();
        model.addAttribute("cohorten", cohorten);
        model.addAttribute("vakken", vakken);
        return "rooster-maken";
    }

    @GetMapping("manager/rooster-maken-cohort-gekozen")
    public String wekenVanCohort(@RequestParam("cohortNaam") String cohortnaam,
                                 Model model) {
        model.addAttribute("cohortnaam", cohortnaam);
        System.out.println(cohortnaam);
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        System.out.println(cohort);
        model.addAttribute("cohort", cohort);
        List<Vak> vakken = vakRepo.findAll();
        model.addAttribute("vakken", vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "rooster-maken-cohort-gekozen";
    }
}

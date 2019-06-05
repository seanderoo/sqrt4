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
import sqrt4.mijninzet.model.Dagdeel;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.CohortRepository;
import sqrt4.mijninzet.repository.DagdeelRespository;
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
    @Autowired
    DagdeelRespository dagdeelRespository;

    @GetMapping("/manager/rooster-maken")
    public String maakRooster(Model model) {
        List<Cohort> cohorten = cohortRepo.findAll();
        model.addAttribute("cohorten", cohorten);
        return "rooster-maken";
    }

    @GetMapping("manager/rooster-maken-cohort-gekozen")
    public String wekenVanCohort(@RequestParam(value = "cohortNaam") String cohortnaam,
                                 Model model) {
        List <Vak> vakkenLijst = vakRepo.findAll();
        model.addAttribute("vakkenLijst", vakkenLijst);
        List<Dagdeel> dagdeelLijst = dagdeelRespository.findAll();
        model.addAttribute("dagdeelLijst", dagdeelLijst);
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Vak> vakken = vakRepo.findAll();
        model.addAttribute("vakken", vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "rooster-maken-cohort-gekozen";
    }
    @PostMapping("manager/rooster-maken-cohort-gekozen")
    public String weekOpslaan(@RequestParam(value = "cohort") String cohortnaam,
                              @ModelAttribute("cohort") Cohort cohort,
                              Model model) {
        System.out.println(cohortnaam);
        System.out.println(cohort);
        return "rooster-maken-cohort-gekozen";
    }
}

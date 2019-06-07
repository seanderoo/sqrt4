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
import sqrt4.mijninzet.model.Vakdagdeel;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.CohortRepository;
import sqrt4.mijninzet.repository.VakdagdeelRespository;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.WeekRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

@Controller
public class RoosterController extends AbstractController {

    @Autowired
    CohortRepository cohortRepo;
    @Autowired
    VakRepository vakRepo;
    @Autowired
    WeekRepository weekRepo;
    @Autowired
    VakdagdeelRespository vakdagdeelRespository;

    @GetMapping("/manager/rooster-maken")
    public String maakRooster(Model model) {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int huidigeJaar = LocalDate.now().getYear();
        int huidigeWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        List<Cohort> cohorten = cohortRepo.findAllByStartJaarIsGreaterThanEqual(huidigeJaar);
        Predicate<Cohort> condition = cohort -> cohort.getStartJaar() == huidigeJaar && cohort.getStartWeek() <= huidigeWeek;
        cohorten.removeIf(condition);

        model.addAttribute("cohorten", cohorten);
        return "rooster-maken";
    }

    @GetMapping("/manager/rooster-maken-karin")
    public String kiesCohort(Model model) {
        List<Cohort> cohorten = cohortRepo.findAll();
        model.addAttribute("cohorten", cohorten);
        return "rooster-maken-karin";
    }

    @GetMapping("manager/rooster-maken-cohort-gekozen")
    public String wekenVanCohort(@RequestParam("cohortNaam") String cohortnaam,
                                 Model model) {
        List<Vak> vakkenLijst = vakRepo.findAll();
        model.addAttribute("vakkenLijst", vakkenLijst);
        List<Vakdagdeel> vakdagdeelList = vakdagdeelRespository.findAll();
        model.addAttribute("vakdagdeelList", vakdagdeelList);
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "rooster-maken-cohort-gekozen";
    }

    @PostMapping("manager/rooster-maken-cohort-gekozen")
    public String weekOpslaan(@RequestParam(value = "cohort") String cohortnaam,
                              @ModelAttribute("cohort") Cohort cohort,
                              Model model) {
        Cohort cohort1 = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute(cohort1);
        return "rooster-maken-cohort-gekozen";
    }

    @GetMapping("manager/rooster-karin")
    public String roosterKarin(@RequestParam("cohortNaam") String cohortnaam,
                               Model model) {
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Vak> vakken = vakRepo.findAll();
        model.addAttribute("vakken", vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "rooster-maken-cohort-gekozen-karin";
    }
    @PostMapping("manager/rooster-karin")
    public String weekOpslaanKarin(@RequestParam("cohortNaam") String cohortnaam,
                              @RequestParam("maOcht") String maOchtVak,
                              @RequestParam("diOcht") String diOchtVak,
                              @RequestParam("woOcht") String woOchtVak,
                              @RequestParam("doOcht") String doOchtVak,
                              @RequestParam("vrOcht") String vrOchtVak,
                              @RequestParam("maMid") String maMidVak,
                              @RequestParam("diMid") String diMidVak,
                              @RequestParam("woMid") String woMidVak,
                              @RequestParam("doMid") String doMidVak,
                              @RequestParam("vrMid") String vrMidVak,
                              @RequestParam("maAvo") String maAvoVak,
                              @RequestParam("diAvo") String diAvoVak,
                              @RequestParam("woAvo") String woAvoVak,
                              @RequestParam("doAvo") String doAvoVak,
                              @RequestParam("vrAvo") String vrAvoVak,
                              Model model) {
        Cohort cohort1 = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute(cohort1);
        Vak vakMaOcht = vakRepo.findByVakNaam(maOchtVak);
        Vak vakDiOcht = vakRepo.findByVakNaam(diOchtVak);
        Vak vakWoOcht = vakRepo.findByVakNaam(woOchtVak);
        Vak vakDoOcht = vakRepo.findByVakNaam(doOchtVak);
        Vak vakVrOcht = vakRepo.findByVakNaam(vrOchtVak);
        Vak vakMaMid = vakRepo.findByVakNaam(maMidVak);
        Vak vakDiMid = vakRepo.findByVakNaam(diMidVak);
        Vak vakWoMid = vakRepo.findByVakNaam(woMidVak);
        Vak vakDoMid = vakRepo.findByVakNaam(doMidVak);
        Vak vakVrMid = vakRepo.findByVakNaam(vrMidVak);
        Vak vakMaAvo = vakRepo.findByVakNaam(maAvoVak);
        Vak vakDiAvo = vakRepo.findByVakNaam(diAvoVak);
        Vak vakWoAvo = vakRepo.findByVakNaam(woAvoVak);
        Vak vakDoAvo = vakRepo.findByVakNaam(doAvoVak);
        Vak vakVrAvo = vakRepo.findByVakNaam(vrAvoVak);
        saveMaandagVakken(cohort1, vakMaOcht, vakMaMid, vakMaAvo);
        saveDinsdagVakken(cohort1, vakDiOcht, vakDiMid, vakDiAvo);
        saveWoensdagVakken(cohort1, vakWoOcht, vakWoMid, vakWoAvo);
        saveDonderdagVakken(cohort1, vakDoOcht, vakDoMid, vakDoAvo);
        saveVrijdagVakken(cohort1, vakVrOcht, vakVrMid, vakVrAvo);
        List<Vak> vakken = vakRepo.findAll();
        model.addAttribute("vakken", vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort1.getId());
        model.addAttribute("weken", weken);
        return "rooster-maken-cohort-gekozen-karin";
    }

    public void saveMaandagVakken(Cohort cohort, Vak maOcht, Vak maMid, Vak maAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weken.get(0);
        week.getMaandag().getOchtend().setVak(maOcht);
        week.getMaandag().getMiddag().setVak(maMid);
        week.getMaandag().getAvond().setVak(maAvo);
        weekRepo.save(week);
    }
    public void saveDinsdagVakken(Cohort cohort, Vak diOcht, Vak diMid, Vak diAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weken.get(0);
        week.getDinsdag().getOchtend().setVak(diOcht);
        week.getDinsdag().getMiddag().setVak(diMid);
        week.getDinsdag().getAvond().setVak(diAvo);
        weekRepo.save(week);
    }
    public void saveWoensdagVakken(Cohort cohort, Vak woOcht, Vak woMid, Vak woAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weken.get(0);
        week.getWoensdag().getOchtend().setVak(woOcht);
        week.getWoensdag().getMiddag().setVak(woMid);
        week.getWoensdag().getAvond().setVak(woAvo);
        weekRepo.save(week);
    }
    public void saveDonderdagVakken(Cohort cohort, Vak doOcht, Vak doMid, Vak doAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weken.get(0);
        week.getDonderdag().getOchtend().setVak(doOcht);
        week.getDonderdag().getMiddag().setVak(doMid);
        week.getDonderdag().getAvond().setVak(doAvo);
        weekRepo.save(week);
    }
    public void saveVrijdagVakken(Cohort cohort, Vak vrOcht, Vak vrMid, Vak vrAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weken.get(0);
        week.getVrijdag().getOchtend().setVak(vrOcht);
        week.getVrijdag().getMiddag().setVak(vrMid);
        week.getVrijdag().getAvond().setVak(vrAvo);
        weekRepo.save(week);
    }

}

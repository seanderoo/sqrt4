package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.CohortRepository;
import sqrt4.mijninzet.repository.DagdeelRepository;
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
    DagdeelRepository dagdeelRepository;


    @GetMapping("/coordinator/rooster-maken")
    public String kiesCohort(Model model) {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int huidigeJaar = LocalDate.now().getYear();
        int huidigeWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        List<Cohort> cohorten = cohortRepo.findAllByStartJaarIsGreaterThanEqual(huidigeJaar);
        Predicate<Cohort> condition = cohort -> cohort.getStartJaar() == huidigeJaar && cohort.getStartWeek() <= huidigeWeek;
        cohorten.removeIf(condition);
        model.addAttribute("cohorten", cohorten);
        return "coordinator/rooster-maken";
    }

    @GetMapping("coordinator/rooster-maken-cohort-gekozen")
    public String roosterKarin(@RequestParam("cohortNaam") String cohortnaam,
                               Model model) {
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Vak> vakken = vakRepo.findAll();
        vakkenSorteren(vakken);
        List<Vak> vakkenZonder = vakkenLijstZonder("Geen les");
        model.addAttribute("vakken", vakken);
        model.addAttribute("vakkenZonder", vakkenZonder);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        model.addAttribute("hashmap", haalToegekendeUrenOp(cohort));
        return "coordinator/rooster-maken-cohort-gekozen";
    }
    @PostMapping("coordinator/rooster-maken-cohort-gekozen")
    public String weekOpslaanKarin(@RequestParam Map<String, String> allParam,
                                   Model model) {
        Cohort cohort1 = cohortRepo.findByCohortNaam(allParam.get("cohortNaam"));
        model.addAttribute(cohort1);
        Vak vakMaOcht = vakRepo.findByVakNaam(allParam.get("maOcht"));
        Vak vakDiOcht = vakRepo.findByVakNaam(allParam.get("diOcht"));
        Vak vakWoOcht = vakRepo.findByVakNaam(allParam.get("woOcht"));
        Vak vakDoOcht = vakRepo.findByVakNaam(allParam.get("doOcht"));
        Vak vakVrOcht = vakRepo.findByVakNaam(allParam.get("vrOcht"));
        Vak vakMaMid = vakRepo.findByVakNaam(allParam.get("maMid"));
        Vak vakDiMid = vakRepo.findByVakNaam(allParam.get("diMid"));
        Vak vakWoMid = vakRepo.findByVakNaam(allParam.get("woMid"));
        Vak vakDoMid = vakRepo.findByVakNaam(allParam.get("doMid"));
        Vak vakVrMid = vakRepo.findByVakNaam(allParam.get("vrMid"));
        Vak vakMaAvo = vakRepo.findByVakNaam(allParam.get("maAvo"));
        Vak vakDiAvo = vakRepo.findByVakNaam(allParam.get("diAvo"));
        Vak vakWoAvo = vakRepo.findByVakNaam(allParam.get("woAvo"));
        Vak vakDoAvo = vakRepo.findByVakNaam(allParam.get("doAvo"));
        Vak vakVrAvo = vakRepo.findByVakNaam(allParam.get("vrAvo"));
        int weekId = Integer.parseInt(allParam.get("week"));
        saveVakkenPerDag(weekId, "maandag", vakMaOcht,vakMaMid, vakMaAvo);
        saveVakkenPerDag(weekId, "dinsdag", vakDiOcht, vakDiMid, vakDiAvo);
        saveVakkenPerDag(weekId, "woensdag", vakWoOcht, vakWoMid, vakWoAvo);
        saveVakkenPerDag(weekId, "donderdag", vakDoOcht, vakDoMid, vakDoAvo);
        saveVakkenPerDag(weekId, "vrijdag", vakVrOcht, vakVrMid, vakVrAvo);
        List<Vak> vakken = vakRepo.findAll();
        vakkenSorteren(vakken);
        model.addAttribute("vakken", vakken);
        List<Vak> vakkenZonder = vakkenLijstZonder("Geen les");
        model.addAttribute("vakkenZonder", vakkenZonder);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort1.getId());
        model.addAttribute("weken", weken);
        model.addAttribute("hashmap", haalToegekendeUrenOp(cohort1));
        return "coordinator/rooster-maken-cohort-gekozen";
    }

    @GetMapping("docent/rooster-kiezen")
    public String docentRoosterBekijken(Model model) {
        model.addAttribute("cohorten", cohortRepo.findAll());
        return "docent/rooster-kiezen";
    }

    @GetMapping("docent/rooster-bekijken")
    public String docentCohortGekozen(@RequestParam("cohortNaam") String cohortnaam,
                                      Model model) {
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "docent/rooster-bekijken";
    }

    final int UREN_PER_DAGDEEL = 4;

    //haalt per vak het aantal uren wat voor dat cohort al opgeslagen is op uit de db (James en Karin)
    public HashMap<String, Integer> haalToegekendeUrenOp(Cohort cohort) {
        HashMap<String, Integer> vakUrenToegekend = new HashMap<>();
        List<Vak> vakken = vakRepo.findAll();
        ArrayList<Integer> vakIds = getVakIds(vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        ArrayList<Integer> dagIds = getDagIds(weken);
        for (int vakId: vakIds) {
            int vakteller = 0;
            for (int dagId: dagIds) {
                vakteller += dagdeelRepository.countByVakVakIdAndDag_Id(vakId,dagId);
            }
            vakUrenToegekend.put(vakRepo.findById(vakId).getVakNaam(), vakteller * UREN_PER_DAGDEEL);
        }
        vakUrenToegekend.remove("Geen les");
        return vakUrenToegekend;
    }

    public ArrayList<Integer> getVakIds(List<Vak> vakken){
        ArrayList<Integer> vakIds = new ArrayList<>();
        for (Vak vak: vakken) {
            vakIds.add(vak.getVakId());
        }
        return vakIds;
    }

    public ArrayList<Integer> getDagIds(List<Week> weken){
        ArrayList<Integer> dagIds = new ArrayList<>();
        for (Week week: weken) {
            dagIds.add(week.getDag("maandag").getId());
            dagIds.add(week.getDag("dinsdag").getId());
            dagIds.add(week.getDag("woensdag").getId());
            dagIds.add(week.getDag("donderdag").getId());
            dagIds.add(week.getDag("vrijdag").getId());
        }
        return dagIds;
    }

    public List<Vak> vakkenSorteren(List<Vak> vakkenlijst){
        Collections.sort(vakkenlijst);
        Vak geenLes = vakRepo.findByVakNaam("Geen les");
        vakkenlijst.remove(geenLes);
        vakkenlijst.add(0, geenLes);
        return vakkenlijst;
    }

    public void saveVakkenPerDag(int weekId, String dagnaam, Vak ochtend, Vak middag, Vak avond) {
        Week week = weekRepo.findById(weekId);
        week.getDag(dagnaam).getOchtend().setVak(ochtend);
        week.getDag(dagnaam).getMiddag().setVak(middag);
        week.getDag(dagnaam).getAvond().setVak(avond);
        weekRepo.save(week);
    }
}

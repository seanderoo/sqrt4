package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.Vakdagdeel;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.*;

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
    @Autowired
    DagdeelRepository dagdeelRepository;

    @GetMapping("/coordinator/rooster-maken")
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
        return "coordinator/rooster-maken";
    }

    @GetMapping("/coordinator/rooster-maken-karin")
    public String kiesCohort(Model model) {
        List<Cohort> cohorten = cohortRepo.findAll();
        model.addAttribute("cohorten", cohorten);
        return "coordinator/rooster-maken-karin";
    }

    @GetMapping("coordinator/rooster-maken-cohort-gekozen")
    public String wekenVanCohort(@RequestParam("cohortNaam") String cohortnaam,
                                 Model model) {
        model.addAttribute("vakdagdeelList", haalVakdagdeellijstOp());
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "coordinator/rooster-maken-cohort-gekozen";
    }

//    @PostMapping("coordinator/rooster-maken-cohort-gekozen")
//    public String weekOpslaan(@RequestParam("cohortje") String cohortId,
//                              @RequestParam(value = "week", required = false) Integer weeknummer,
//                              @RequestParam("maandagochtend") String maandagochtend,
//                              @RequestParam("maandagmiddag") String maandagmiddag,
//                              @RequestParam("maandagavond") String maandagavond,
//                              @RequestParam("dinsdagochtend") String dinsdagochtend,
//                              @RequestParam("dinsdagmiddag") String dinsdagmiddag,
//                              @RequestParam("dinsdagavond") String dinsdagavond,
//                              @RequestParam("woensdagochtend") String woensdagochtend,
//                              @RequestParam("woensdagmiddag") String woensdagmiddag,
//                              @RequestParam("woensdagavond") String woensdagavond,
//                              @RequestParam("donderdagochtend") String donderdagochtend,
//                              @RequestParam("donderdagmiddag") String donderdagmiddag,
//                              @RequestParam("donderdagavond") String donderdagavond,
//                              @RequestParam("vrijdagochtend") String vrijdagochtend,
//                              @RequestParam("vrijdagmiddag") String vrijdagmiddag,
//                              @RequestParam("vrijdagavond") String vrijdagavond,
//                              Model model) {
//        final int OCHTEND = 0;
//        final int MIDDAG = 1;
//        final int AVOND = 2;
//
//        Cohort cohort = cohortRepo.findById(Integer.parseInt(cohortId));
//
//        String[] dagdeelIdsMaandag = {maandagochtend, maandagmiddag, maandagavond};
//        String[] dagdeelIdsDinsdag = {dinsdagochtend, dinsdagmiddag, dinsdagavond};
//        String[] dagdeelIdsWoensdag = {woensdagochtend, woensdagmiddag, woensdagavond};
//        String[] dagdeelIdsDonderdag = {donderdagochtend, donderdagmiddag, donderdagavond};
//        String[] dagdeelIdsVrijdag = {vrijdagochtend, vrijdagmiddag, vrijdagavond};
//
//        List<Vak> vakkenMaandag = haalVakkenOpMetId(dagdeelIdsMaandag);
//        List<Vak> vakkenDinsdag = haalVakkenOpMetId(dagdeelIdsDinsdag);
//        List<Vak> vakkenWoensdag = haalVakkenOpMetId(dagdeelIdsWoensdag);
//        List<Vak> vakkenDonderdag = haalVakkenOpMetId(dagdeelIdsDonderdag);
//        List<Vak> vakkenVrijdag = haalVakkenOpMetId(dagdeelIdsVrijdag);
//
//        saveVakkenPerDag(cohort, weeknummer, "maandag", vakkenMaandag.get(OCHTEND), vakkenMaandag.get(MIDDAG),
//                vakkenMaandag.get(AVOND));
//        saveVakkenPerDag(cohort, weeknummer, "dinsdag", vakkenDinsdag.get(OCHTEND), vakkenDinsdag.get(MIDDAG),
//                vakkenDinsdag.get(AVOND));
//        saveVakkenPerDag(cohort, weeknummer, "woensdag", vakkenWoensdag.get(OCHTEND), vakkenWoensdag.get(MIDDAG),
//                vakkenWoensdag.get(AVOND));
//        saveVakkenPerDag(cohort, weeknummer, "donderdag", vakkenDonderdag.get(OCHTEND), vakkenDonderdag.get(MIDDAG),
//                vakkenDonderdag.get(AVOND));
//        saveVakkenPerDag(cohort, weeknummer, "vrijdag", vakkenVrijdag.get(OCHTEND), vakkenVrijdag.get(MIDDAG),
//                vakkenVrijdag.get(AVOND));
//
//        model.addAttribute("vakdagdeelList", haalVakdagdeellijstOp());
//        model.addAttribute("cohort", cohort);
//        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
//        model.addAttribute("weken", weken);
//
//        return "coordinator/rooster-maken-cohort-gekozen";
//    }

    @GetMapping("coordinator/rooster-maken-cohort-gekozen-karin")
    public String roosterKarin(@RequestParam("cohortNaam") String cohortnaam,
                               Model model) {
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Vak> vakken = vakRepo.findAll();
        vakkenSorteren(vakken);
        List<Vak> vakkenZonder = vakRepo.findAll();
        Vak geenLes = vakRepo.findByVakNaam("Geen les");
        vakkenZonder.remove(geenLes);
        Collections.sort(vakkenZonder);
        model.addAttribute("vakken", vakken);
        model.addAttribute("vakkenZonder", vakkenZonder);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        model.addAttribute("hashmap", haalToegekendeUrenOp(cohort));
        return "coordinator/rooster-maken-cohort-gekozen-karin";
    }
    @PostMapping("coordinator/rooster-maken-cohort-gekozen-karin")
    public String weekOpslaanKarin(@RequestParam("cohortNaam") String cohortnaam,
                              @RequestParam(value = "week", required = false) int weekId,
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
        saveVakkenPerDag(weekId, "maandag", vakMaOcht,vakMaMid, vakMaAvo);
        saveVakkenPerDag(weekId, "dinsdag", vakDiOcht, vakDiMid, vakDiAvo);
        saveVakkenPerDag(weekId, "woensdag", vakWoOcht, vakWoMid, vakWoAvo);
        saveVakkenPerDag(weekId, "donderdag", vakDoOcht, vakDoMid, vakDoAvo);
        saveVakkenPerDag(weekId, "vrijdag", vakVrOcht, vakVrMid, vakVrAvo);
        List<Vak> vakken = vakRepo.findAll();
        vakkenSorteren(vakken);
        model.addAttribute("vakken", vakken);
        List<Vak> vakkenZonder = vakRepo.findAll();
        Vak geenles = vakRepo.findByVakNaam("Geen les");
        vakkenZonder.remove(geenles);
        Collections.sort(vakkenZonder);
        model.addAttribute("vakkenZonder", vakkenZonder);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort1.getId());
        model.addAttribute("weken", weken);
        model.addAttribute("hashmap", haalToegekendeUrenOp(cohort1));
        return "coordinator/rooster-maken-cohort-gekozen-karin";
    }

    final int UREN_PER_DAGDEEL = 4;

    //haalt per vak het aantal uren wat voor dat cohort al opgeslagen is op uit de db (James en Karin)
    public HashMap<String, Integer> haalToegekendeUrenOp(Cohort cohort) {
        HashMap<String, Integer> vakUrenToegekend = new HashMap<>();
        List<Vak> vakken = vakRepo.findAll();
        ArrayList<Integer> vakIds = new ArrayList<>();
        for (Vak vak: vakken) {
            vakIds.add(vak.getVakId());
        }
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        ArrayList<Integer> dagIds = new ArrayList<>();
        for (Week week: weken) {
            dagIds.add(week.getDag("maandag").getId());
            dagIds.add(week.getDag("dinsdag").getId());
            dagIds.add(week.getDag("woensdag").getId());
            dagIds.add(week.getDag("donderdag").getId());
            dagIds.add(week.getDag("vrijdag").getId());
        }
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

    private List<Vak> haalVakkenOpMetId(String[] ids) {
        ArrayList<Vak> vakkenVanEenDag = new ArrayList<>();
        for (String id : ids) {
            vakkenVanEenDag.add(haalVakOp(id));
        }
        return vakkenVanEenDag;
    }

    private Vak haalVakOp(String id) {
        Vakdagdeel vakdagdeel = vakdagdeelRespository.findById(Integer.parseInt(id));
        if (vakdagdeel == null) {
            return vakRepo.findByVakNaam("Geen les");
        } else {
            int vakId = vakdagdeel.getVak().getVakId();
            return vakRepo.findById(vakId);
        }
    }

    private List<Vakdagdeel> haalVakdagdeellijstOp() {
        List<Vakdagdeel> vakdagdeellijst = vakdagdeelRespository.findAll();
        List<Vakdagdeel> schoneVakdagdeellijst = new ArrayList<>();
        for (Vakdagdeel vdd : vakdagdeellijst) {
            if (vdd.getVak() != null) {
                schoneVakdagdeellijst.add(vdd);
            }
        }
        Collections.sort(vakdagdeellijst);
        return vakdagdeellijst;
    }
}

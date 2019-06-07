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
        List<Vakdagdeel> vakdagdeelList = vakdagdeelRespository.findAll();
        List<Vakdagdeel> schoneVakdagdeellijst = new ArrayList<>();
        for (Vakdagdeel vdd : vakdagdeelList) {
            if (vdd.getVak() != null) {
                schoneVakdagdeellijst.add(vdd);
            }
        }
        model.addAttribute("vakdagdeelList", schoneVakdagdeellijst);
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        return "rooster-maken-cohort-gekozen";
    }

    @PostMapping("manager/rooster-maken-cohort-gekozen")
    public String weekOpslaan(@RequestParam("cohortje") String cohortId,
                              @RequestParam("maandagochtend") String maandagochtend,
                              @RequestParam("maandagmiddag") String maandagmiddag,
                              @RequestParam("maandagavond") String maandagavond,
                              @RequestParam("dinsdagochtend") String dinsdagochtend,
                              @RequestParam("dinsdagmiddag") String dinsdagmiddag,
                              @RequestParam("dinsdagavond") String dinsdagavond,
                              @RequestParam("woensdagochtend") String woensdagochtend,
                              @RequestParam("woensdagmiddag") String woensdagmiddag,
                              @RequestParam("woensdagavond") String woensdagavond,
                              @RequestParam("donderdagochtend") String donderdagochtend,
                              @RequestParam("donderdagmiddag") String donderdagmiddag,
                              @RequestParam("donderdagavond") String donderdagavond,
                              @RequestParam("vrijdagochtend") String vrijdagochtend,
                              @RequestParam("vrijdagmiddag") String vrijdagmiddag,
                              @RequestParam("vrijdagavond") String vrijdagavond,
                              Model model) {
        Cohort cohort = cohortRepo.findById(Integer.parseInt(cohortId));

        Vakdagdeel vddMaO = vakdagdeelRespository.findById(Integer.parseInt(maandagochtend));
        int vakMaOId = vddMaO.getVak().getVakId();
        Vak vakMaO = vakRepo.findById(vakMaOId);
        Vakdagdeel vddMaM = vakdagdeelRespository.findById(Integer.parseInt(maandagmiddag));
        int vakMaMId = vddMaM.getVak().getVakId();
        Vak vakMaM = vakRepo.findById(vakMaMId);
        Vakdagdeel vddMaA = vakdagdeelRespository.findById(Integer.parseInt(maandagavond));
        int vakMaAId = vddMaA.getVak().getVakId();
        Vak vakMaA = vakRepo.findById(vakMaAId);
        saveMaandagVakken(cohort, vakMaO, vakMaM, vakMaA);

        Vakdagdeel vddDiO = vakdagdeelRespository.findById(Integer.parseInt(dinsdagochtend));
        int vakDiOId = vddDiO.getVak().getVakId();
        Vak vakDiO = vakRepo.findById(vakDiOId);
        Vakdagdeel vddDiM = vakdagdeelRespository.findById(Integer.parseInt(dinsdagmiddag));
        int vakDiMId = vddDiM.getVak().getVakId();
        Vak vakDiM = vakRepo.findById(vakDiMId);
        Vakdagdeel vddDiA = vakdagdeelRespository.findById(Integer.parseInt(dinsdagavond));
        int vakDiAId = vddDiA.getVak().getVakId();
        Vak vakDiA = vakRepo.findById(vakDiAId);
        saveDinsdagVakken(cohort, vakDiO, vakDiM, vakDiA);


        Vakdagdeel vddWoO = vakdagdeelRespository.findById(Integer.parseInt(woensdagochtend));
        int vakWoOId = vddWoO.getVak().getVakId();
        Vak vakWoO = vakRepo.findById(vakWoOId);
        Vakdagdeel vddWoM = vakdagdeelRespository.findById(Integer.parseInt(woensdagmiddag));
        int vakWoMId = vddWoM.getVak().getVakId();
        Vak vakWoM = vakRepo.findById(vakWoMId);
        Vakdagdeel vddWoA = vakdagdeelRespository.findById(Integer.parseInt(woensdagavond));
        int vakWoAId = vddWoA.getVak().getVakId();
        Vak vakWoA = vakRepo.findById(vakWoAId);
        saveWoensdagVakken(cohort, vakWoO, vakWoM, vakWoA);


        Vakdagdeel vddDoO = vakdagdeelRespository.findById(Integer.parseInt(donderdagochtend));
        int vakDoOId = vddDoO.getVak().getVakId();
        Vak vakDoO = vakRepo.findById(vakDoOId);
        Vakdagdeel vddDoM = vakdagdeelRespository.findById(Integer.parseInt(donderdagmiddag));
        int vakDoMId = vddDoM.getVak().getVakId();
        Vak vakDoM = vakRepo.findById(vakDoMId);
        Vakdagdeel vddDoA = vakdagdeelRespository.findById(Integer.parseInt(donderdagavond));
        int vakDoAId = vddDoA.getVak().getVakId();
        Vak vakDoA = vakRepo.findById(vakDoAId);
        saveDonderdagVakken(cohort, vakDoO, vakDoM, vakDoA);


        Vakdagdeel vddVrO = vakdagdeelRespository.findById(Integer.parseInt(vrijdagochtend));
        int vakVrOId = vddVrO.getVak().getVakId();
        Vak vakVroO = vakRepo.findById(vakVrOId);
        Vakdagdeel vddVrM = vakdagdeelRespository.findById(Integer.parseInt(vrijdagmiddag));
        int vakVrMId = vddVrM.getVak().getVakId();
        Vak vakVrM = vakRepo.findById(vakVrMId);
        Vakdagdeel vddVrA = vakdagdeelRespository.findById(Integer.parseInt(vrijdagavond));
        int vakVrAId = vddVrA.getVak().getVakId();
        Vak vakVrA = vakRepo.findById(vakVrAId);
        saveDonderdagVakken(cohort, vakVroO, vakVrM, vakVrA);

        List<Vakdagdeel> vakdagdeelList = vakdagdeelRespository.findAll();
        List<Vakdagdeel> schoneVakdagdeellijst = new ArrayList<>();
        for (Vakdagdeel vdd : vakdagdeelList) {
            if (vdd.getVak() != null) {
                schoneVakdagdeellijst.add(vdd);
            }
        }
        model.addAttribute("vakdagdeelList", schoneVakdagdeellijst);
        model.addAttribute("cohort", cohort);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);

        return "rooster-maken-cohort-gekozen";
    }

    @GetMapping("manager/rooster-maken-cohort-gekozen-karin")
    public String roosterKarin(@RequestParam("cohortNaam") String cohortnaam,
                               Model model) {
        Cohort cohort = cohortRepo.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);
        List<Vak> vakken = vakRepo.findAll();
        model.addAttribute("vakken", vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);
        int teller = 1;
        model.addAttribute("teller", teller);
        return "rooster-maken-cohort-gekozen-karin";
    }
    @PostMapping("manager/rooster-maken-cohort-gekozen-karin")
    public String weekOpslaanKarin(@RequestParam("cohortNaam") String cohortnaam,
                              @RequestParam(value = "week", required = false) int weeknummer,
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
        System.out.println(weeknummer);
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
        saveMaandagVakken(cohort1, weeknummer, vakMaOcht, vakMaMid, vakMaAvo);
        saveDinsdagVakken(cohort1, weeknummer, vakDiOcht, vakDiMid, vakDiAvo);
        saveWoensdagVakken(cohort1, weeknummer, vakWoOcht, vakWoMid, vakWoAvo);
        saveDonderdagVakken(cohort1, weeknummer, vakDoOcht, vakDoMid, vakDoAvo);
        saveVrijdagVakken(cohort1, weeknummer, vakVrOcht, vakVrMid, vakVrAvo);
        List<Vak> vakken = vakRepo.findAll();
        model.addAttribute("vakken", vakken);
        List<Week> weken = weekRepo.findWeeksByCohortId(cohort1.getId());
        model.addAttribute("weken", weken);
        return "rooster-maken-cohort-gekozen-karin";
    }

    public void saveMaandagVakken(Cohort cohort, int weekNummer, Vak maOcht, Vak maMid, Vak maAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weekRepo.findByWeekNummerAndCohort(weekNummer, cohort);
        week.getMaandag().getOchtend().setVak(maOcht);
        week.getMaandag().getMiddag().setVak(maMid);
        week.getMaandag().getAvond().setVak(maAvo);
        weekRepo.save(week);
    }
    public void saveDinsdagVakken(Cohort cohort, int weekNummer, Vak diOcht, Vak diMid, Vak diAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weekRepo.findByWeekNummerAndCohort(weekNummer, cohort);
        week.getDinsdag().getOchtend().setVak(diOcht);
        week.getDinsdag().getMiddag().setVak(diMid);
        week.getDinsdag().getAvond().setVak(diAvo);
        weekRepo.save(week);
    }
    public void saveWoensdagVakken(Cohort cohort, int weekNummer, Vak woOcht, Vak woMid, Vak woAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weekRepo.findByWeekNummerAndCohort(weekNummer, cohort);
        week.getWoensdag().getOchtend().setVak(woOcht);
        week.getWoensdag().getMiddag().setVak(woMid);
        week.getWoensdag().getAvond().setVak(woAvo);
        weekRepo.save(week);
    }
    public void saveDonderdagVakken(Cohort cohort, int weekNummer, Vak doOcht, Vak doMid, Vak doAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weekRepo.findByWeekNummerAndCohort(weekNummer, cohort);
        week.getDonderdag().getOchtend().setVak(doOcht);
        week.getDonderdag().getMiddag().setVak(doMid);
        week.getDonderdag().getAvond().setVak(doAvo);
        weekRepo.save(week);
    }
    public void saveVrijdagVakken(Cohort cohort, int weekNummer, Vak vrOcht, Vak vrMid, Vak vrAvo) {
        ArrayList<Week> weken = (ArrayList) weekRepo.findWeeksByCohortId(cohort.getId());
        Week week = weekRepo.findByWeekNummerAndCohort(weekNummer, cohort);
        week.getVrijdag().getOchtend().setVak(vrOcht);
        week.getVrijdag().getMiddag().setVak(vrMid);
        week.getVrijdag().getAvond().setVak(vrAvo);
        weekRepo.save(week);
    }

    public int omzettenNaarWerkendeInt(String nietWerkendeInt) {
        final int WERKENDE_LEGE_INT = 0;
        int werkendeInt = Integer.parseInt(nietWerkendeInt);
        if (nietWerkendeInt == null) {
            werkendeInt = WERKENDE_LEGE_INT;
        }
        return werkendeInt;

    }

}

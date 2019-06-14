package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Dag;
import sqrt4.mijninzet.model.Beschikbaarheid.Dagdeel;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

@Controller
public class KoppelDocentenController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CohortRepository cohortRepository;
    @Autowired
    private WeekRepository weekRepository;
    @Autowired
    private DagRepository dagRepository;
    @Autowired
    private DagdeelRepository dagdeelRepository;

    @GetMapping("roosteraar/docenten-koppelen-kies-cohort")
    public String koppelDocenten(Model model) {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int huidigeJaar = LocalDate.now().getYear();
        int huidigeWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        List<Cohort> cohortList = cohortRepository.findAllByStartJaarIsGreaterThanEqual(huidigeJaar);
        Predicate<Cohort> condition = cohort -> cohort.getStartJaar() == huidigeJaar && cohort.getStartWeek() <= huidigeWeek;
//        cohortList.removeIf(condition);

        model.addAttribute("cohortList", cohortList);
        return "roosteraar/docenten-koppelen-kies-cohort";
    }

    @GetMapping("roosteraar/docenten-koppelen-gekozen-cohort")
    public String docentenKoppelen(@RequestParam("cohortNaam") String cohortnaam, Model model) {
        Cohort cohort = cohortRepository.findByCohortNaam(cohortnaam);
        model.addAttribute("cohort", cohort);

        List<Week> weken = weekRepository.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);

        List<User> docentList = userRepository.findAllByRolesContaining("DOCENT");
        model.addAttribute("docentList", docentList);
        return "roosteraar/docenten-koppelen-gekozen-cohort";
    }

    @PostMapping("roosteraar/docenten-koppelen-gekozen-cohort")
    public String slaWeekOp(@RequestParam("cohortNaam") String cohortNaam,
                            @RequestParam(value = "weekId", required = false) int weekId,
                            @RequestParam("maOcht") long maOchtDoc,
                            @RequestParam("diOcht") long diOchtDoc,
                            @RequestParam("woOcht") long woOchtDoc,
                            @RequestParam("doOcht") long doOchtDoc,
                            @RequestParam("vrOcht") long vrOchtDoc,
                            @RequestParam("maMid") long maMidDoc,
                            @RequestParam("diMid") long diMidDoc,
                            @RequestParam("woMid") long woMidDoc,
                            @RequestParam("doMid") long doMidDoc,
                            @RequestParam("vrMid") long vrMidDoc,
                            @RequestParam("maAvo") long maAvoDoc,
                            @RequestParam("diAvo") long diAvoDoc,
                            @RequestParam("woAvo") long woAvoDoc,
                            @RequestParam("doAvo") long doAvoDoc,
                            @RequestParam("vrAvo") long vrAvoDoc,
                            Model model) {


        Cohort cohort = cohortRepository.findByCohortNaam(cohortNaam);
        Week week = weekRepository.findById(weekId);

        User docent1 = userRepository.findUserById(maOchtDoc);
        User docent2 = userRepository.findUserById(maMidDoc);
        User docent3 = userRepository.findUserById(maAvoDoc);

        User docent4 = userRepository.findUserById(diOchtDoc);
        User docent5 = userRepository.findUserById(diMidDoc);
        User docent6 = userRepository.findUserById(diAvoDoc);

        User docent7 = userRepository.findUserById(woOchtDoc);
        User docent8 = userRepository.findUserById(woMidDoc);
        User docent9 = userRepository.findUserById(woAvoDoc);

        User docent10 = userRepository.findUserById(doOchtDoc);
        User docent11 = userRepository.findUserById(doMidDoc);
        User docent12 = userRepository.findUserById(doAvoDoc);

        User docent13 = userRepository.findUserById(vrOchtDoc);
        User docent14 = userRepository.findUserById(vrMidDoc);
        User docent15 = userRepository.findUserById(vrAvoDoc);

        saveDocentPerDag(weekId, "maandag", docent1, docent2, docent3);
        saveDocentPerDag(weekId, "dinsdag", docent4, docent5, docent6);
        saveDocentPerDag(weekId, "woensdag", docent7, docent8, docent9);
        saveDocentPerDag(weekId, "donderdag", docent10, docent11, docent12);
        saveDocentPerDag(weekId, "vrijdag", docent13, docent14, docent15);

        return "roosteraar/docenten-koppelen-gekozen-cohort";
    }

    public void saveDocentPerDag(int weekId, String dagnaam, User ochtend, User middag, User avond) {
        Week week = weekRepository.findById(weekId);
        week.getDag(dagnaam).getOchtend().setDocent(ochtend);
        week.getDag(dagnaam).getMiddag().setDocent(middag);
        week.getDag(dagnaam).getAvond().setDocent(avond);
        weekRepository.save(week);
    }
}

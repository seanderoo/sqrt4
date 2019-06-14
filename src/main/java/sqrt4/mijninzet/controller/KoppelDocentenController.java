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
    public String slaWeekOp(@RequestParam(value = "cohortNaam", required = false) String cohortnaam,
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

        System.out.println("dit is de weekId: " + weekId);
        Cohort cohort = cohortRepository.findByCohortNaam(cohortnaam);
        Dagdeel dagdeel = new Dagdeel();


        Week week = weekRepository.findById(weekId);
        Dag maandag = week.getDag("maandag");
        Dag dinsdag = week.getDag("dinsdag");
        Dag woensdag = week.getDag("woensdag");
        Dag donderdag = week.getDag("donderdag");
        Dag vrijdag = week.getDag("vrijdag");

        User user1 = userRepository.findUserById(maOchtDoc);
        User user2 = userRepository.findUserById(maMidDoc);
        User user3 = userRepository.findUserById(maAvoDoc);
        User user4 = userRepository.findUserById(diOchtDoc);
        User user5 = userRepository.findUserById(diMidDoc);
        User user6 = userRepository.findUserById(diAvoDoc);
        User user7 = userRepository.findUserById(woOchtDoc);
        User user8 = userRepository.findUserById(woMidDoc);
        User user9 = userRepository.findUserById(woAvoDoc);

        woensdag.getOchtend().setDocent(user7);
        woensdag.getMiddag().setDocent(user8);
        woensdag.getAvond().setDocent(user9);

        User user10 = userRepository.findUserById(doOchtDoc);
        User user11 = userRepository.findUserById(doMidDoc);
        User user12 = userRepository.findUserById(doAvoDoc);

        donderdag.getOchtend().setDocent(user10);
        donderdag.getMiddag().setDocent(user11);
        donderdag.getAvond().setDocent(user12);

        User user13 = userRepository.findUserById(vrOchtDoc);
        User user14 = userRepository.findUserById(vrMidDoc);
        User user15 = userRepository.findUserById(vrAvoDoc);

        vrijdag.getOchtend().setDocent(user13);
        vrijdag.getMiddag().setDocent(user14);
        vrijdag.getAvond().setDocent(user15);

        dagRepository.save(maandag);
        dagRepository.save(dinsdag);
        dagRepository.save(woensdag);
        dagRepository.save(donderdag);
        dagRepository.save(vrijdag);
        return "roosteraar/docenten-koppelen-gekozen-cohort";
    }
}

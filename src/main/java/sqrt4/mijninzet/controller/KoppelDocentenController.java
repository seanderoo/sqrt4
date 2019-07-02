package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Voorkeur;
import sqrt4.mijninzet.repository.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

@Controller
public class KoppelDocentenController extends AbstractController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CohortRepository cohortRepository;
    @Autowired
    private WeekRepository weekRepository;
    @Autowired
    private VoorkeurenRepository voorkeurenRepository;

    public static final String[] DAGEN = {"maandag", "dinsdag", "woensdag", "donderdag", "vrijdag"};
    public static final String[] DAGDELENOCHTEND = {"MAO","DIO","WOO","DOO","VRO"};
    public static final String[] DAGDELENMIDDAG = {"MAM","DIM","WOM","DOM","VRM"};
    public static final String[] DAGDELENAVOND = {"MAA","DIA","WOA","DOA","VRA"};

    @GetMapping("roosteraar/docenten-koppelen-kies-cohort")
    public String koppelDocenten(Model model) {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int huidigeJaar = LocalDate.now().getYear();
        int huidigeWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        List<Cohort> cohortList = cohortRepository.findAllByStartJaarIsGreaterThanEqual(huidigeJaar);
        Predicate<Cohort> condition = cohort -> cohort.getStartJaar() == huidigeJaar && cohort.getStartWeek() <= huidigeWeek;
        cohortList.removeIf(condition);

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

        Map<String, List<User>> map = mapMaker(DAGDELENOCHTEND, DAGDELENMIDDAG, DAGDELENAVOND, DAGEN);
        model.addAllAttributes(map);

        List<Voorkeur> voorkeuren = voorkeurenRepository.findAll();
        String voorkeurenString = zetOmNaarString(voorkeuren);
        model.addAttribute("voorkeuren", voorkeurenString);
        return "roosteraar/docenten-koppelen-gekozen-cohort";
    }

    @PostMapping("roosteraar/docenten-koppelen-gekozen-cohort")
    public String slaWeekOp(@RequestParam Map<String, String> allParams, Model model) {
        Set<String> keys = allParams.keySet();
        ArrayList<User> docenten = new ArrayList<>();
        String[] array = keys.toArray(new String[keys.size()]);
        for (int i = 2; i < array.length ; i++) {
            docenten.add(userRepository.findUserById(Long.parseLong(allParams.get(array[i]))));
        }

        saveDocentPerWeek(Integer.parseInt(allParams.get(array[1])), DAGEN, docenten);

        Cohort cohort = cohortRepository.findByCohortNaam(allParams.get(array[0]));
        model.addAttribute("cohort", cohort);
        List<Week> weken = weekRepository.findWeeksByCohortId(cohort.getId());
        model.addAttribute("weken", weken);

        model.addAllAttributes(mapMaker(DAGDELENOCHTEND, DAGDELENMIDDAG, DAGDELENAVOND, DAGEN));
        return "roosteraar/docenten-koppelen-gekozen-cohort";
    }

    private String zetOmNaarString(List<Voorkeur> voorkeuren) {
        StringBuilder bob = new StringBuilder();
        for (int i = 0; i < voorkeuren.size(); i++) {
            Voorkeur voorkeur = voorkeuren.get(i);
            bob.append(voorkeur.getVoorkeurGebruiker())
                    .append("_")
                    .append(voorkeur.getUser().getId())
                    .append("_").
                    append(voorkeur.getVak().getVakNaam());
            if (i != (voorkeuren.size()-1)) {
                bob.append(",");
            }
        }
        String string = bob.toString();
        System.out.println("Odysseus sprak: \"De Trojanen zullen geen array in een string verwachten: " + string + "\"");
        return string;
    }

    private void saveDocentPerDag(int weekId, String dagnaam, User ochtend, User middag, User avond) {
        Week week = weekRepository.findById(weekId);
        week.getDag(dagnaam).getOchtend().setDocent(ochtend);
        week.getDag(dagnaam).getMiddag().setDocent(middag);
        week.getDag(dagnaam).getAvond().setDocent(avond);
        weekRepository.save(week);
    }

    private void saveDocentPerWeek(int weekId, String[] dagen, ArrayList<User> docenten) {
        saveDocentPerDag(weekId, dagen[0], docenten.get(0), docenten.get(5), docenten.get(10));
        saveDocentPerDag(weekId, dagen[1], docenten.get(1), docenten.get(6), docenten.get(11));
        saveDocentPerDag(weekId, dagen[2], docenten.get(2), docenten.get(7), docenten.get(12));
        saveDocentPerDag(weekId, dagen[3], docenten.get(3), docenten.get(8), docenten.get(13));
        saveDocentPerDag(weekId, dagen[4], docenten.get(4), docenten.get(9), docenten.get(14));
    }

    private Map<String, List<User>> mapMaker(String[] dagdelenOchtend, String[] dagdelenMiddag, String[] dagdelenAvond, String[] dagen) {
        Map<String, List<User>> map = new HashMap<>();
        for (int i = 0; i < DAGEN.length; i++) {
            map.put(dagdelenOchtend[i], getDocentenInOchtend(dagen[i]));
            map.put(dagdelenMiddag[i], getDocentenInMiddag(dagen[i]));
            map.put(dagdelenAvond[i], getDocentenInAvond(dagen[i]));
        }
        return map;
    }


}

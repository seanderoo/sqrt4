package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Semester;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.repository.AlgemeneBeschikbaarheidRepository;

import java.util.List;

@Controller
public class AlgemeneBeschikbaarheidController extends AbstractController {
    private static final int EERSTE_SEMESTER = 0;

    @Autowired
    AlgemeneBeschikbaarheidRepository algBesRepo;

    @GetMapping("/docent/algemene-beschikbaarheid")
    public String AlgemeneBeschikbaarheid(@RequestParam(name = "gekozenSemester", required = false) String actiefSemester,
                                          Model model) {
        Semester cohort = algBesRepo.findBySemesterNaamAndUser(actiefSemester, voegActiveUserToe());

        if (cohort == null) {
            cohort = algBesRepo.findAllByUser(voegActiveUserToe()).get(EERSTE_SEMESTER);
        }

        System.out.println("dit is het cohort: " + cohort);
        //Dit hieronder werkt nog niet.
        Week standaardWeek = null;
        try {
            standaardWeek = cohort.getFirstWeek();
            System.out.println("dit is de eerste week: " + standaardWeek);
        } catch (NullPointerException e) {
            standaardWeek = new Week();
            System.out.println("standaard week opgehaald");
        }
        model.addAttribute("standaardWeek", standaardWeek);
        model.addAttribute("actiefSemester", actiefSemester);

        return "algemene-beschikbaarheid";
    }

    @ModelAttribute("semesters")
    public List<Semester> semesters() {
        List<Semester> semesterlijst = algBesRepo.findAll();

        return semesterlijst;
    }

    @PostMapping(value = "/docent/algemene-beschikbaarheid-ander-cohort")
    public String anderCohortKiezen(@RequestParam("gekozenSemester") String actiefSemester,
                                    Model model) {
        Semester semester = algBesRepo.findBySemesterNaamAndUser(actiefSemester, voegActiveUserToe());
        Week week = semester.getFirstWeek();
        model.addAttribute("standaardWeek", week);
        model.addAttribute("actiefSemester", actiefSemester);
        return "algemene-beschikbaarheid";
    }

    @PostMapping(value = "/docent/algemene-beschikbaarheid-updaten")
    //moet ik hier een aparte pagina voor aanmaken? Mss toch maar op beschikbaarheid blijven.
    public String updateAlgemeneBeschikbaarheid(@RequestParam("maandagochtend") boolean maOBeschikbaar,
                                                @RequestParam("maandagmiddag") boolean maMBeschikbaar,
                                                @RequestParam("maandagavond") boolean maABeschikbaar,
                                                @RequestParam("dinsdagochtend") boolean diOBeschikbaar,
                                                @RequestParam("dinsdagmiddag") boolean diMBeschikbaar,
                                                @RequestParam("dinsdagavond") boolean diABeschikbaar,
                                                @RequestParam("woensdagochtend") boolean woOBeschikbaar,
                                                @RequestParam("woensdagmiddag") boolean woMBeschikbaar,
                                                @RequestParam("woensdagavond") boolean woABeschikbaar,
                                                @RequestParam("donderdagochtend") boolean doOBeschikbaar,
                                                @RequestParam("donderdagmiddag") boolean doMBeschikbaar,
                                                @RequestParam("donderdagavond") boolean doABeschikbaar,
                                                @RequestParam("vrijdagochtend") boolean vrOBeschikbaar,
                                                @RequestParam("vrijdagmiddag") boolean vrMBeschikbaar,
                                                @RequestParam("vrijdagavond") boolean vrABeschikbaar,
                                                @RequestParam("gekozenSemester") String actiefSemester,
                                                Model model) {

        Week algemeneWeek = new Week();

        algemeneWeek.getDag("maandag").setOchtend(maOBeschikbaar);
        algemeneWeek.getDag("maandag").setMiddag(maMBeschikbaar);
        algemeneWeek.getDag("maandag").setAvond(maABeschikbaar);
        algemeneWeek.getDag("dinsdag").setOchtend(diOBeschikbaar);
        algemeneWeek.getDag("dinsdag").setMiddag(diMBeschikbaar);
        algemeneWeek.getDag("dinsdag").setAvond(diABeschikbaar);
        algemeneWeek.getDag("woensdag").setOchtend(woOBeschikbaar);
        algemeneWeek.getDag("woensdag").setMiddag(woMBeschikbaar);
        algemeneWeek.getDag("woensdag").setAvond(woABeschikbaar);
        algemeneWeek.getDag("donderdag").setOchtend(doOBeschikbaar);
        algemeneWeek.getDag("donderdag").setMiddag(doMBeschikbaar);
        algemeneWeek.getDag("donderdag").setAvond(doABeschikbaar);
        algemeneWeek.getDag("vrijdag").setOchtend(vrOBeschikbaar);
        algemeneWeek.getDag("vrijdag").setMiddag(vrMBeschikbaar);
        algemeneWeek.getDag("vrijdag").setAvond(vrABeschikbaar);


        Semester semester = algBesRepo.findBySemesterNaamAndUser(actiefSemester, voegActiveUserToe());
        semester.beschikbaarheidAanpassen(algemeneWeek);

        algBesRepo.save(semester);
        model.addAttribute("standaardWeek", algemeneWeek);
        System.out.println("De geposte week heeft maandag: " + algemeneWeek.getMaandag().getOchtend());

        return "algemene-beschikbaarheid"; //Is de pagina waar je vervolgens heengestuurd wordt?
    }
}

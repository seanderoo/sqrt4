package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sqrt4.mijninzet.model.Beschikbaarheid.Dag;
import sqrt4.mijninzet.model.Beschikbaarheid.Semester;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.AlgemeneBeschikbaarheidRepository;
import sqrt4.mijninzet.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AlgemeneBeschikbaarheidController {

    @Autowired
    AlgemeneBeschikbaarheidRepository algBesRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping("/algemene-beschikbaarheid")
    public String AlgemeneBeschikbaarheid(Model model,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(name = "gekozenSemester", required = false) String actiefSemester) {
        model.addAttribute("name", name);

        Semester cohort = algBesRepo.findBySemesterNaamAndUser(actiefSemester, voegActiveUserToe());
        Week standaardWeek = null;
        try {
            standaardWeek = cohort.getFirstWeek();
        } catch (NullPointerException e) {
            standaardWeek = new Week();
        }
        model.addAttribute("standaardWeek", standaardWeek);

        return "algemene-beschikbaarheid";
    }

    @ModelAttribute("semesters")
    public List<Semester> semesters() {
        List<Semester> semesterlijst = algBesRepo.findAll();

        return semesterlijst;
    }

    @ModelAttribute("week")
    public List<Dag> week() {
        List<Dag> week = new ArrayList<>();
        return week;
    }

    @PostMapping(value = "/algemene-beschikbaarheid")     //moet ik hier een aparte pagina voor aanmaken? Mss toch maar op beschikbaarheid blijven.
    public String bewaarAlgemeneBeschikbaarheid(@RequestParam("maandagochtend") boolean maOBeschikbaar,
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
        System.out.println(semester);

        algBesRepo.save(semester);
        return "algemene-beschikbaarheid"; //Is de pagina waar je vervolgens heengestuurd wordt?
    }

    private User voegActiveUserToe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return userRepo.findByUsername(userName);
    }
}

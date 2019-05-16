package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AlgemeneBeschikbaarheidController {
    private User currentUser = new User("linusband", "123","Linus","Band"); //Moet eigenlijk aan de ingelogde user gekoppeld worden.

    @Autowired
    AlgemeneBeschikbaarheidRepository repo;

    @GetMapping("/algemene-beschikbaarheid")
    public String AlgemeneBeschikbaarheid(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Karin") String name) {
        model.addAttribute("name", name);

        return "algemene-beschikbaarheid";
    }

    @ModelAttribute("semester")
    public Semester semester() {
        Semester semester = new Semester();
        return semester;
    }

    @ModelAttribute("semesters")
    public List<Semester> semesters() {
        currentUser.setId(1);
        List<Semester> semesterlijst = repo.findAllByUser(currentUser); //neemt deze wel de weken en dagen mee?
//        List<Semester> semesterlijst = new ArrayList<>();
        semesterlijst.add(new Semester(4, 2020, 29));
        semesterlijst.add(new Semester(30, 2020, 3));
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
                                                @RequestParam("gekozenSemester") String semesternaam){
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


//        System.out.println(algemeneWeek);
//        System.out.println();
//        Semester semester = repo.findBySemesterNaamAndUser(semesternaam, currentUser);
        Semester semester = new Semester();

        System.out.println(semester);

        semester.beschikbaarheidAanpassen(algemeneWeek);
//        System.out.println(semester); //Hier gaat 't mis omdat bij 1970 de semesterList leeg is. Is dit omdat ie de weken en dagen niet mee ophaalt?


        repo.save(semester);
        return "algemene-beschikbaarheid"; //Is de pagina waar je vervolgens heengestuurd wordt?

    }
}

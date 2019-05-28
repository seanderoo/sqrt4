package sqrt4.mijninzet.controller;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Dag;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.Incident;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.IncidentregistratieRepository;
import sqrt4.mijninzet.repository.UserRepository;;
import sqrt4.mijninzet.repository.WeekRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
public class IncidentregistratieController extends AbstractController {

    @Autowired
    IncidentregistratieRepository repo;

    @Autowired
    WeekRepository repoWeek;

    @Autowired
    UserRepository repoUser;

    @GetMapping("/docent/incidentregistratie")
    public String Incidentregistratie(Model model) {
        List<Incident> incidentList = repo.findAllByUser(voegActiveUserToe());
        model.addAttribute("incidentLijst", incidentList);
        return "incidentregistratie";
    }

    @ModelAttribute("dag")
    public Dag dag() {
        Dag dag = new Dag();
        return dag;
    }

    @PostMapping(value = "/docent/incidentregistratie")
    public String registreerIncident(@RequestParam("ochtend") boolean ochtend,
                                     @RequestParam("middag") boolean middag,
                                     @RequestParam("avond") boolean avond,
                                     @RequestParam("datum") String datum,
                                     Model model) {
        LocalDate date = LocalDate.parse(datum);
        System.out.println(date);
        Incident temp = new Incident(date, ochtend, middag, avond);
        temp.setUser(voegActiveUserToe());
        System.out.println(temp);
        repo.save(temp);
        List<Incident> incidentList = repo.findAllByUser(voegActiveUserToe());
        //        Collections.sort(incidentList); Hoe ging het ook alweer???
        model.addAttribute("incidentLijst", incidentList);
        return "incidentregistratie";
    }

//    @PostMapping(value = "/docent/incidentregistratie")
//    public String verwijderIncident(@RequestParam("Verwijderen")LocalDate date, Model model){
//        repo.deleteByDatum(date);
//        List<Incident> incidentList = repo.findAll();
//        model.addAttribute("incidentLijst", incidentList);
//        return "incidentregistratie";
//
//    }


//    @PostMapping(value ="/incidentregistratie")
//    public String zoekWeekOp (@RequestParam("selectedDate") String gekozendatum, Model model){
//        gekozenDag = gekozendatum;
//
//        LocalDate date = LocalDate.parse(gekozenDag);
//        Dag tempDag = repo.findDagByDatum(date);
//        int weekNr = tempDag.getWeekNummer();
//        int weekJr = tempDag.getJaarNummer();
//        Week tempWeek = repoWeek.findByJaarNummerAndWeekNummer(weekJr, weekNr);
//        System.out.println(tempWeek);
//        model.addAttribute("geselecteerdeWeek" , tempWeek);
//        model.addAttribute("maandagdatum", tempWeek.getDag("maandag").getDatum());
//        model.addAttribute("dinsdagdatum", tempWeek.getDag("dinsdag").getDatum());
//        model.addAttribute("woensdagdatum", tempWeek.getDag("woensdag").getDatum());
//        model.addAttribute("donderdagdatum", tempWeek.getDag("donderdag").getDatum());
//        model.addAttribute("vrijdagdatum", tempWeek.getDag("vrijdag").getDatum());
//        model.addAttribute("maandagochtend", tempWeek.getDag("maandag").getOchtendAsInt());
//        System.out.println(tempWeek.getDag("maandag").getOchtendAsInt());
//        model.addAttribute("dinsdagochtend", tempWeek.getDag("dinsdag").getOchtendAsInt());
//        model.addAttribute("woensdagochtend", tempWeek.getDag("woensdag").getOchtendAsInt());
//        model.addAttribute("donderdagochtend", tempWeek.getDag("donderdag").getOchtendAsInt());
//        model.addAttribute("vrijdagochtend", tempWeek.getDag("vrijdag").getOchtendAsInt());
//        return "incidentregistratie";
//    }

//    @GetMapping(value = "/incidentregistratie-huidigeWeek")
//    public String opgehaaldeWeek(Week huidigeWeek, Model model) {
//        model.addAttribute("huidigeWeek", huidigeWeek);
//
//        return "incidentregistratie";
//    }

//    @GetMapping(value = "/incidentregistratie")
//    public String zoekSelectedWeek (String dag, Model model){
//
//
//       return "incidentregistratie";
//}



}

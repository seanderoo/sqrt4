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
import sqrt4.mijninzet.repository.IncidentregistratieRepository;
import sqrt4.mijninzet.repository.WeekRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class IncidentregistratieController {
    private String gekozenDag;
    private LocalDate date;
    private Week week;

    @Autowired
    IncidentregistratieRepository repo;

    @Autowired
    WeekRepository repoWeek;

    @GetMapping("/incidentregistratie")
    public String Incidentregistratie(){
        return "incidentregistratie";
    }

//    @ModelAttribute("week")
//    public Week week(){
//        Week week = repo.findByDatum(gekozenDag);
//        System.out.println("Dit is de geprinte: "+ week);
//        return week;
//    }
    @ModelAttribute("dag")
    public Dag dag() {
        Dag dag = new Dag();
        return dag;
    }

    @PostMapping(value ="/incidentregistratie")
    public String zoekWeekOp (@RequestParam("selectedDate") String gekozendatum, Model model){
        gekozenDag = gekozendatum;
        LocalDate date = LocalDate.parse(gekozenDag);
        Dag tempDag = repo.findDagByDatum(date);
        int weekNr = tempDag.getWeekNummer();
        int weekJr = tempDag.getJaarNummer();
        Week tempWeek = repoWeek.findByJaarNummerAndWeekNummer(weekJr, weekNr);
        model.addAttribute("geselecteerdeWeek" , tempWeek);
        return "incidentregistratie";
    }

    @GetMapping(value = "/incidentregistratie/huidigeWeek")
    public String opgehaaldeWeek(Week huidigeWeek, Model model) {
        model.addAttribute("huidigeWeek", huidigeWeek);
        model.addAttribute("maandagdatum", huidigeWeek.getDag("maandag").getDatum());
        model.addAttribute("dinsdagdatum", huidigeWeek.getDag("dinsdag").getDatum());
        model.addAttribute("woensdagdatum", huidigeWeek.getDag("woensdag").getDatum());
        model.addAttribute("donderdagdatum", huidigeWeek.getDag("donderdag").getDatum());
        model.addAttribute("vrijdagdatum", huidigeWeek.getDag("vrijdag").getDatum());
        return "incidentregistratie";
    }

//    @GetMapping(value = "/incidentregistratie")
//    public String zoekSelectedWeek (String dag, Model model){
//
//
//       return "incidentregistratie";
//}



}

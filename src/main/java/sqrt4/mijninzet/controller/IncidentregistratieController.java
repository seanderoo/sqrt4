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
        Dag temp = repo.findDagByDatum(date);
        int weekNr = temp.getWeekNummer();
        int weekJr = temp.getJaarNummer();
        Week tempWeek = repo.findByJaarNummerAndWeekNummer(weekJr,weekNr);
        model.addAttribute("geselecteerdeWeek" , tempWeek);
        return "incidentregistratie";
    }

//    @GetMapping(value = "/incidentregistratie")
//    public String zoekSelectedWeek (String dag, Model model){
//
//
//       return "incidentregistratie";
//}



}

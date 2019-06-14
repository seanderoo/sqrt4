package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Dag;
import sqrt4.mijninzet.model.Beschikbaarheid.Dagdeel;
import sqrt4.mijninzet.model.Incident;
import sqrt4.mijninzet.repository.IncidentregistratieRepository;
import sqrt4.mijninzet.repository.UserRepository;
import sqrt4.mijninzet.repository.WeekRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Controller
public class IncidentController extends AbstractController {

    @Autowired
    IncidentregistratieRepository repoIncident;

    @Autowired
    WeekRepository repoWeek;

    @Autowired
    UserRepository repoUser;

    @GetMapping("/docent/incidentregistratie")
    public String incidentRegistratie(Model model) {
        List<Incident> statusAfgehandeld = repoIncident.findAllByUserAndStatusNotContaining(voegActiveUserToe(),
                "in behandeling");

        List<Incident> incidentList = repoIncident.findAllByUserAndStatusContaining(voegActiveUserToe(),
                "in behandeling");
        model.addAttribute("statusInBehandeling", incidentList);
        model.addAttribute("statusAfgehandeld", statusAfgehandeld);
        return "docent/incidentregistratie";
    }

    @GetMapping("/manager/incidenten-beheer")
    public String incidentBeheer(Model model) {
        List<Incident> incidentList = repoIncident.findAllByStatusIsContaining("in behandeling");
        List<Incident> afgehandeldLijst = repoIncident.findAllByStatusIsNotContaining("in behandeling");
        model.addAttribute("user", voegActiveUserToe());
        model.addAttribute("afgehandeldLijst", afgehandeldLijst);
        model.addAttribute("aanvraagLijst", incidentList);
        return "manager/incidenten-beheer";
    }

    @PostMapping("/manager/incidenten-beheer")
    public String incidentBeheer(Incident incident,
                                 @RequestParam(value = "status") String statusString,
                                 @RequestParam(value = "inputManagerText", required = false) String textManager,
                                 Model model) {

        incident.setStatus(incident.getStatus());
        incident.setManagerToelichting(incident.getManagerToelichting());
        repoIncident.save(incident);


//            @RequestParam (value = "incident_id") Long incident_id,
//                                  @RequestParam(value = "inputManagerText", required = false) String opmerkingManager,
//                                  @RequestParam("status") String aanvraagBeoordeling,
//                                              Model model){
//
//        Incident temp = repoIncident.findIncidentById(incident_id);
//        temp.setManagerToelichting(opmerkingManager);
//        System.out.println(temp.getManagerToelichting());
//        temp.setStatus(temp.getStatus());
//
//        System.out.println("Status " + temp.getStatus());
//        repoIncident.save(temp);
        List<Incident> incidentList = repoIncident.findAllByStatusIsContaining("in behandeling");
        List<Incident> afgehandeldLijst = repoIncident.findAllByStatusIsNotContaining("in behandeling");
        model.addAttribute("afgehandeldLijst", afgehandeldLijst);
        model.addAttribute("aanvraagLijst", incidentList);
        return "manager/incidenten-beheer";
    }

    @ModelAttribute("dag")
    public Dag dag() {
        Dag dag = new Dag();
        dag.setOchtend(new Dagdeel(true, dag));
        dag.setMiddag(new Dagdeel(true, dag));
        dag.setAvond(new Dagdeel(true, dag));
        return dag;
    }

    @PostMapping(value = "/docent/incidentregistratie")
    public String registreerIncident(@RequestParam("ochtend") boolean ochtend,
                                     @RequestParam("middag") boolean middag,
                                     @RequestParam("avond") boolean avond,
                                     @RequestParam("datum") String datum,
                                     @RequestParam("opmerkingGebruiker") String opmerkingGebruiker,
                                     Model model) {
        LocalDate date = LocalDate.parse(datum);
        List<Incident> incidentListPerUser = repoIncident.findAllByUser(voegActiveUserToe());
        boolean bestaatAl = false;
        for (Incident incident : incidentListPerUser) {
            if ( incident.getDatum().equals(date) ) {
                bestaatAl = true;
            }
        }

        if ( bestaatAl ) {
            Incident temp = repoIncident.findByDatumAndUser(date, voegActiveUserToe());
            System.out.println("Dit is na bestaatAl: " + temp);
            temp.setOchtend(ochtend);
            temp.setMiddag(middag);
            temp.setAvond(avond);
            temp.setOpmerkingGebruiker(opmerkingGebruiker);
            repoIncident.save(temp);
        } else {
            Incident temp = new Incident(date, ochtend, middag, avond, opmerkingGebruiker);
            temp.setUser(voegActiveUserToe());
            repoIncident.save(temp);
        }
        List<Incident> statusAfgehandeld = repoIncident.findAllByUserAndStatusNotContaining(voegActiveUserToe(),
                "in behandeling");
        List<Incident> incidentList = repoIncident.findAllByUserAndStatusContaining(voegActiveUserToe(),
                "in behandeling");
        model.addAttribute("statusInBehandeling", incidentList);
        model.addAttribute("statusAfgehandeld", statusAfgehandeld);
//        List<Incident> incidentList = repoIncident.findAllByUser(voegActiveUserToe());
//        Collections.sort(incidentList, new IncidentComparator());
//        model.addAttribute("incidentLijst", incidentList);
        return "docent/incidentregistratie";
    }

    class IncidentComparator implements Comparator<Incident> {
        @Override
        public int compare(Incident a, Incident b) {
            return a.getDatum().isBefore(b.getDatum()) ? -1 : a.getDatum().isEqual(b.getDatum()) ? 0 : 1;
        }
    }
}

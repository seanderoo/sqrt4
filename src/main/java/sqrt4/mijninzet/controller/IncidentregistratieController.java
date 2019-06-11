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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class IncidentregistratieController extends AbstractController {

    @Autowired
    IncidentregistratieRepository repoIncident;

    @Autowired
    WeekRepository repoWeek;

    @Autowired
    UserRepository repoUser;

    @GetMapping("/docent/incidentregistratie")
    public String Incidentregistratie(Model model) {
        List<Incident> incidentList = repoIncident.findAllByUser(voegActiveUserToe());
        model.addAttribute("incidentLijst", incidentList);
        return "docent/incidentregistratie";
    }

    @ModelAttribute("dag")
    public Dag dag() {
        Dag dag = new Dag();
        dag.setOchtend(new Dagdeel(true,dag));
        dag.setMiddag(new Dagdeel(true,dag));
        dag.setAvond(new Dagdeel(true,dag));
        return dag;
    }

    @PostMapping(value = "/docent/incidentregistratie")
    public String registreerIncident(@RequestParam("ochtend") boolean ochtend,
                                     @RequestParam("middag") boolean middag,
                                     @RequestParam("avond") boolean avond,
                                     @RequestParam("datum") String datum,
                                     Model model) {
        LocalDate date = LocalDate.parse(datum);
        if ( repoIncident.existsById(date) ) {
            Incident temp = repoIncident.findByDatum(date);
            temp.setOchtend(ochtend);
            temp.setMiddag(middag);
            temp.setAvond(avond);
            repoIncident.save(temp);
        } else {
            Incident temp = new Incident(date, ochtend, middag, avond);
            temp.setUser(voegActiveUserToe());
            repoIncident.save(temp);
        }
        List<Incident> incidentList = repoIncident.findAllByUser(voegActiveUserToe());
        Collections.sort(incidentList, new IncidentComparator());
        model.addAttribute("incidentLijst", incidentList);
        return "docent/incidentregistratie";
    }

    class IncidentComparator implements Comparator<Incident> {
        @Override
        public int compare(Incident a, Incident b) {
            return a.getDatum().isBefore(b.getDatum()) ? -1 : a.getDatum().isEqual(b.getDatum()) ? 0 : 1;
        }
    }
}

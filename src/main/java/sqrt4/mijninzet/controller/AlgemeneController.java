package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.CohortRepository;
import sqrt4.mijninzet.repository.VoorkeurenRepository;
import sqrt4.mijninzet.repository.WeekRepository;

@RestController
@RequestMapping("/ajax")
public class AlgemeneController {

    public static final int AANTAL_WEKEN_IN_JAAR = 52;

    @Autowired
    private VoorkeurenRepository voorkeurenRepository;
    @Autowired
    private WeekRepository weekRepo;

    @RequestMapping(value = "/voorkeuren/{user}/{vak}", method = RequestMethod.POST)
    public Integer testRest(@PathVariable User user, @PathVariable Vak vak) {
        Integer preference = 0;
        try {
            Integer databaseVoorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vak, user).getVoorkeurGebruiker();
            if (databaseVoorkeur == null) {
                return 0;
            } else {
                return databaseVoorkeur;
            }
        } catch (NullPointerException e) {
        }
        return preference;
    }

//    @RequestMapping(value = "/ajax/roosteraar/docent-koppelen/{cohortnummer}/{weeknummer}", method = RequestMethod.POST)
//    public Week haalWeekOp(@PathVariable Cohort cohort, @PathVariable Week weeknummer) {
//
//    }

    @RequestMapping(value = "/{user}", method = RequestMethod.POST)
    public String testRest2(@PathVariable User user) {
        return "";
    }

    @RequestMapping(value = "/roosteraar/docent-koppelen/{cohort}", method = RequestMethod.POST)
    public int getAantalWekenGeselecteerdeCohort(@PathVariable Cohort cohort) {
        int aantalWeken = cohort.hoeveelWekenInJaar(cohort.getStartJaar());
        int startJaar = cohort.getStartJaar();
        int eindJaar = cohort.getEindJaar();
        int startWeek = cohort.getStartWeek();
        int eindWeek = cohort.getEindWeek();
        if (startJaar == eindJaar) {
            aantalWeken = eindWeek - startWeek;
        } else if (eindJaar > startJaar) {
            aantalWeken = (aantalWeken - startWeek) + eindWeek;
        }
        return aantalWeken;

    }
    //haalt de vakken op die al voor die week zijn ogeslagen (Karin)
    @RequestMapping(value = "/manager/rooster-maken-cohort-gekozen-karin/{week}", method = RequestMethod.GET)
    public String vakAlOpgeslagen(@PathVariable Week week) {
        System.out.println("week is: " + week);
        //week is niet de juiste week. Als je week 6 kiest maakt hij daar weekid 6 van en haalt vervolgens de week die daarbij hoort uit de db op.
        String vaknaam = week.getDag("maandag").getOchtend().getVak().getVakNaam();
        System.out.println("De naam van het vak is: " + vaknaam);
        return vaknaam;
    }
}

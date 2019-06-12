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

@RestController
@RequestMapping("/ajax")
public class AlgemeneController {

    public static final int AANTAL_WEKEN_IN_JAAR = 52;

    @Autowired
    private VoorkeurenRepository voorkeurenRepository;
    @Autowired
    private CohortRepository cohortRepository;

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

    //(Karin)
    @RequestMapping(value = "/manager/rooster-maken-cohort-gekozen-karin/{cohort}", method = RequestMethod.GET)
    public int aantalUrenToegekend(@PathVariable Cohort cohort) {
        //Michel haalde overal cohort weg en toen werkte het wel.
        //aantal uren wat al toegekend is in dit cohort ophalen uit de database
        System.out.println("cohort is: " + cohort);
        int aantalUren = 0;
        System.out.println("Het aantal uren is: " + aantalUren);
        return aantalUren;
    }
}

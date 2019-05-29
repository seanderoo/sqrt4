package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.repository.WeekRepository;

@Controller
public class AlgemeneBeschikbaarheidController extends AbstractController {
    public static final int NUMMER_ALGEMENE_WEEK = 0;

    @Autowired
    WeekRepository weekRepo;

    @GetMapping("/docent/algemene-beschikbaarheid")
    public String AlgemeneBeschikbaarheid(Model model) {
        Week algemeneWeek = new Week();

        try{
            algemeneWeek = weekRepo.findByWeekNummerAndUser(NUMMER_ALGEMENE_WEEK,voegActiveUserToe());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

        if (algemeneWeek == null) {
            algemeneWeek = new Week();
        }

        algemeneWeek.setUser(voegActiveUserToe());

        weekRepo.save(algemeneWeek);
        System.out.println(algemeneWeek);

        model.addAttribute("algemeneWeek", algemeneWeek);

        return "algemene-beschikbaarheid";
    }

    @PostMapping(value = "/docent/algemene-beschikbaarheid-updaten")     //moet ik hier een aparte pagina voor aanmaken? Mss toch maar op beschikbaarheid blijven.
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
                                                Model model) {

        Week algemeneWeek = weekRepo.findByWeekNummerAndUser(NUMMER_ALGEMENE_WEEK,voegActiveUserToe());

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

        weekRepo.save(algemeneWeek);
        model.addAttribute("algemeneWeek", algemeneWeek);

        return "algemene-beschikbaarheid";
    }
}

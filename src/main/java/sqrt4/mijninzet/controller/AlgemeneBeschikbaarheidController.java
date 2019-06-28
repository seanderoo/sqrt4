package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.WeekRepository;

import java.util.Map;

@RequestMapping("/docent")
@Controller
public class AlgemeneBeschikbaarheidController extends AbstractController {
    public static final int NUMMER_ALGEMENE_WEEK = 0;

    @Autowired
    WeekRepository weekRepo;

    @GetMapping("/algemene-beschikbaarheid")
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

        User actieveUser = voegActiveUserToe();
        algemeneWeek.setUser(actieveUser);
        weekRepo.save(algemeneWeek);

        model.addAttribute("algemeneWeek", algemeneWeek);

        return "docent/algemene-beschikbaarheid";
    }

    @PostMapping(value = "/algemene-beschikbaarheid-updaten")
    public String updateAlgemeneBeschikbaarheid(@RequestParam Map<String, Boolean> allParams,
                                                @RequestParam("maandagochtend") boolean maOBeschikbaar,
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

        System.out.println(allParams);
        Week algemeneWeek = weekRepo.findByWeekNummerAndUser(NUMMER_ALGEMENE_WEEK,voegActiveUserToe());

        algemeneWeek.getDag("maandag").getOchtend().setBeschikbaar(maOBeschikbaar);
        algemeneWeek.getDag("maandag").getMiddag().setBeschikbaar(maMBeschikbaar);
        algemeneWeek.getDag("maandag").getAvond().setBeschikbaar(maABeschikbaar);
        algemeneWeek.getDag("dinsdag").getOchtend().setBeschikbaar(diOBeschikbaar);
        algemeneWeek.getDag("dinsdag").getMiddag().setBeschikbaar(diMBeschikbaar);
        algemeneWeek.getDag("dinsdag").getAvond().setBeschikbaar(diABeschikbaar);
        algemeneWeek.getDag("woensdag").getOchtend().setBeschikbaar(woOBeschikbaar);
        algemeneWeek.getDag("woensdag").getMiddag().setBeschikbaar(woMBeschikbaar);
        algemeneWeek.getDag("woensdag").getAvond().setBeschikbaar(woABeschikbaar);
        algemeneWeek.getDag("donderdag").getOchtend().setBeschikbaar(doOBeschikbaar);
        algemeneWeek.getDag("donderdag").getMiddag().setBeschikbaar(doMBeschikbaar);
        algemeneWeek.getDag("donderdag").getAvond().setBeschikbaar(doABeschikbaar);
        algemeneWeek.getDag("vrijdag").getOchtend().setBeschikbaar(vrOBeschikbaar);
        algemeneWeek.getDag("vrijdag").getMiddag().setBeschikbaar(vrMBeschikbaar);
        algemeneWeek.getDag("vrijdag").getAvond().setBeschikbaar(vrABeschikbaar);

        weekRepo.save(algemeneWeek);
        model.addAttribute("algemeneWeek", algemeneWeek);

        return "docent/algemene-beschikbaarheid";
    }
}

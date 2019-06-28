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
import java.util.Set;

@RequestMapping("/docent")
@Controller
public class AlgemeneBeschikbaarheidController extends AbstractController {
    public static final int NUMMER_ALGEMENE_WEEK = 0;
    public static final int MAAK_MIDDAGINDEX = 5;
    public static final int MAAK_AVONDINDEX = 10;


    @Autowired
    WeekRepository weekRepo;

    public static final String[] DAGEN = {"maandag", "dinsdag", "woensdag", "donderdag", "vrijdag"};

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
    public String updateAlgemeneBeschikbaarheid(@RequestParam Map<String, String> allParams, Model model) {
        Set<String> keys = allParams.keySet();
        String[] beschikbaarheden = keys.toArray(new String[keys.size()]);
        Week algemeneWeek = weekRepo.findByWeekNummerAndUser(NUMMER_ALGEMENE_WEEK,voegActiveUserToe());

        for (int i = 0; i < DAGEN.length; i++) {
            algemeneWeek.getDag(DAGEN[i]).getOchtend().setBeschikbaar(Boolean.parseBoolean(allParams.get(beschikbaarheden[i])));
            algemeneWeek.getDag(DAGEN[i]).getMiddag().setBeschikbaar(Boolean.parseBoolean(allParams.get(beschikbaarheden[i + MAAK_MIDDAGINDEX])));
            algemeneWeek.getDag(DAGEN[i]).getAvond().setBeschikbaar(Boolean.parseBoolean(allParams.get(beschikbaarheden[i + MAAK_AVONDINDEX])));
        }

        weekRepo.save(algemeneWeek);
        model.addAttribute("algemeneWeek", algemeneWeek);

        return "docent/algemene-beschikbaarheid";
    }
}

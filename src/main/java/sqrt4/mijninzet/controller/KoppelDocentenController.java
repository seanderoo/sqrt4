package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.CohortRepository;
import sqrt4.mijninzet.repository.UserRepository;
import java.time.LocalDate;
import java.util.*;

@Controller
public class KoppelDocentenController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CohortRepository cohortRepository;

    @GetMapping("/roosteraar/docenten-koppelen")
    public String koppelDocenten(Model model) {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int huidigeJaar = LocalDate.now().getYear();
        int huidigeWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        List<Cohort> cohortList = cohortRepository.findAllByStartJaarIsGreaterThanEqual(huidigeJaar);
        List<User> docentList = userRepository.findAllByRolesContaining("DOCENT");

        model.addAttribute("cohortList", cohortList);
        model.addAttribute("docentList", docentList);

        return "docenten-koppelen";
    }

}

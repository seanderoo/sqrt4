package sqrt4.mijninzet.controller;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.CohortRepository;
import sqrt4.mijninzet.repository.UserRepository;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class KoppelDocentenController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CohortRepository cohortRepository;

    @GetMapping("/roosteraar/docenten-koppelen")
    public String koppelDocenten(Model model) {
        List<Cohort> cohortList = cohortRepository.findAll();
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);

        for (Cohort cohort: cohortList) {
            if (cohort.getStartJaar() <= LocalDate.now().getYear() && cohort.getStartWeek() <= calendar.get(Calendar.WEEK_OF_YEAR)) {
                cohortList.remove(cohort);
            }
        }

//        int aantalWekenCohort = cohort.aantalWekenInCohort(cohort.getStartWeek(), cohort.getEindWeek());
        List<User> docentList = userRepository.findAllByRolesContaining("DOCENT");
        model.addAttribute("docentList", docentList);
        model.addAttribute("cohortList", cohortList);




        return "docenten-koppelen";
    }
}

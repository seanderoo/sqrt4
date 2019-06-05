package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.UserRepository;

import java.util.List;

@Controller
public class KoppelDocentenController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/roosteraar/docenten-koppelen")
    public String koppelDocenten(Model model) {
        Cohort cohort = new Cohort();
        int aantalWekenCohort = cohort.aantalWekenInCohort(cohort.getStartWeek(), cohort.getEindWeek());
        List<User> docentList = userRepository.findAllByRolesContaining("DOCENT");
        model.addAttribute("docentList", docentList);

        for (User user: docentList) {
            System.out.println(user.getUsername());
        }


        return "docenten-koppelen";
    }
}

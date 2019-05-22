package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.config.UserPrincipal;
import sqrt4.mijninzet.model.Beschikbaarheid.Semester;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.AlgemeneBeschikbaarheidRepository;
import sqrt4.mijninzet.repository.UserRepository;

@Controller
public class MaaksemesterController extends AbstractController {

    @Autowired
    AlgemeneBeschikbaarheidRepository abRepo;

    @GetMapping("/maaksemester")
    public String Maaksemester( Model model, Semester semester){
        return "maaksemester";
    }
    Semester semester = new Semester();

    @PostMapping("/maaksemester")
    public String nieuwSemester(Model model,
                                @ModelAttribute("semester") Semester semester,
                                @RequestParam("cohortNummer") int cohortNummer,
                                @RequestParam ("startJaar") int startJaar,
                                @RequestParam("startWeek") int startWeek,
                                @RequestParam ("eindWeek") int eindWeek){
        semester = new Semester(cohortNummer, startWeek, startJaar, eindWeek);
        semester.setUser(voegActiveUserToe());
        abRepo.save(semester);
        model.addAttribute("user", voegActiveUserToe());
        return "home";
    }
}

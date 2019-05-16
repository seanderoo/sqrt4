package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqrt4.mijninzet.model.Beschikbaarheid.Semester;
import sqrt4.mijninzet.repository.AlgemeneBeschikbaarheidRepository;

@Controller
public class MaaksemesterController {

    @Autowired
    AlgemeneBeschikbaarheidRepository abRepo;

    @GetMapping("/maaksemester")
    public String Maaksemester( Model model, Semester semester){
        return "maaksemester";
    }
    Semester semester = new Semester();

    @PostMapping("/maaksemester")
    public String nieuwSemester(@ModelAttribute("semester") Semester semester,
                                @RequestParam ("startJaar") int startJaar,
                                @RequestParam("startWeek") int startWeek,
                                @RequestParam ("eindWeek") int eindWeek){
        semester = new Semester(startWeek,startJaar,eindWeek);
        abRepo.save(semester);
        return "gebruiker-toegevoegd";
    }
}

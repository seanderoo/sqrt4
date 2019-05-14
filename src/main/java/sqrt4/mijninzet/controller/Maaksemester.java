package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Maaksemester {

    @GetMapping("/maaksemester")
    public String Maaksemester(Model model){
        return "maaksemester";
    }
}

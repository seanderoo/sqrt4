package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KoppelDocentenController {

    @GetMapping("/roosteraar/docenten-koppelen")
    public String koppelDocenten() {
        return "docenten-koppelen";
    }
}

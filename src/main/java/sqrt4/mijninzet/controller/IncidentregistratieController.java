package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IncidentregistratieController {

    @GetMapping("/incidentregistratie")
    public String Incidentregistratie(){
        return "incidentregistratie";
    }
}

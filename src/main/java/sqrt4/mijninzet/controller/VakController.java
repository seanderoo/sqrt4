package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sqrt4.mijninzet.repository.VakRepository;

@Controller
public class VakController {

    @Autowired
    VakRepository vakRepository;

    @GetMapping("/manager/vak-aanmaken")
    public String vakAanmaken() {
        return "vak-aanmaken";
    }

    @GetMapping("/manager/vak-rooster")
    public String vakRoosterMaken() {
        return "vak-rooster";
    }
}

package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.model.Dagdeel;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.VakRepository;

import java.util.List;

@Controller
public class RoosterTestController {

    @Autowired
    private VakRepository vakRepository;

    @GetMapping("/roostertest")
    public String roosterTest(Model model){
        List <Vak> vakkenLijst = vakRepository.findAll();
        model.addAttribute("vakkenLijst", vakkenLijst);
        return "/roostertest";
    }

    @PostMapping("/roostertest")
    public String roosterTest2(@ModelAttribute("dagdeelLijst") Vak vak, Model model){
        List <Vak> vakkenLijst = vakRepository.findAll();
        model.addAttribute("vakkenLijst", vakkenLijst);
        List<Dagdeel> dagdeelLijst = vak.getDagdelen();
        model.addAttribute("dagdeelLijst", dagdeelLijst);
        return "/roostertest";
    }

    @PostMapping("/roostertest-dagdelen")
    public String roosterTest3(){

        return "/roostertest";
    }

}

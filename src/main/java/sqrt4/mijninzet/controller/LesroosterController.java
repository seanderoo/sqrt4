package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Vakdagdeel;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.VakdagdeelRespository;

import java.util.List;

@Controller
public class LesroosterController {

    @Autowired
    private VakRepository vakRepository;

    @Autowired
    private VakdagdeelRespository vakdagdeelRespository;

    @GetMapping("/manager/lesrooster-aanmaken")
    public String roosterTest(Model model){
        List <Vak> vakkenLijst = vakRepository.findAll();
        model.addAttribute("vakkenLijst", vakkenLijst);
        List<Vakdagdeel> vakdagdeelLijst = vakdagdeelRespository.findAll();
        model.addAttribute("vakdagdeelLijst", vakdagdeelLijst);
        return "lesrooster-aanmaken";
    }


}

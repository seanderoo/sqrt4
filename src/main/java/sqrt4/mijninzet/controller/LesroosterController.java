package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.model.Dagdeel;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.DagdeelRespository;
import sqrt4.mijninzet.repository.VakRepository;

import java.util.List;

@Controller
public class LesroosterController {

    @Autowired
    private VakRepository vakRepository;

    @Autowired
    private DagdeelRespository dagdeelRespository;

    @GetMapping("/manager/lesrooster-aanmaken")
    public String roosterTest(Model model){
        List <Vak> vakkenLijst = vakRepository.findAll();
        model.addAttribute("vakkenLijst", vakkenLijst);
        List<Dagdeel> dagdeelLijst = dagdeelRespository.findAll();
        model.addAttribute("dagdeelLijst", dagdeelLijst);
        return "/lesrooster-aanmaken";
    }


}

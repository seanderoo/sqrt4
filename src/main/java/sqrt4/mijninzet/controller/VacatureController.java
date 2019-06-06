package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.Vacature;
import sqrt4.mijninzet.repository.VacatureRepository;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class VacatureController extends AbstractController{

    @Autowired
    VacatureRepository vacrepo;

    @GetMapping("/nieuwe-vacature")
    public String nieuweVacature(){
        return "admin/nieuwe-vacature";
    }

    @GetMapping("/overzicht-vacatures")
    public String overzichtVacatures(Model model){
        List<Vacature> vacaturelijst = vacrepo.findAll();
        model.addAttribute("vacatureLijst", vacaturelijst);
        return "admin/overzicht-vacatures";
    }

    @PostMapping("/overzicht-vacatures")
    public String verwijderVacature(@RequestParam("Verwijder") int id, Model model){
        vacrepo.deleteById(id);
        List<Vacature> vacaturelijst = vacrepo.findAll();
        model.addAttribute("vacatureLijst", vacaturelijst);
        return "admin/overzicht-vacatures";
    }


    @PostMapping("/vacature-toegevoegd")
    public String nieuweGebruiker(@ModelAttribute("vacature") Vacature vacature) {
        vacrepo.save(vacature);
        return "admin/vacature-toegevoegd";
    }
}
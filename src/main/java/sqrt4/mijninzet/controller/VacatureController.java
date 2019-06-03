package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sqrt4.mijninzet.model.Vacature;
import sqrt4.mijninzet.repository.VacatureRepository;

@RequestMapping("/admin")
@Controller
public class VacatureController extends AbstractController{

    @Autowired
    VacatureRepository vacrepo;

    @GetMapping("/nieuwe-vacature")
    public String nieuweVacature(){
    return "/admin/nieuwe-vacature";
    }

    @PostMapping("/vacature-toegevoegd")
    public String nieuweGebruiker(@ModelAttribute("vacature") Vacature vacature) {
        vacrepo.save(vacature);
        return "/admin/vacature-toegevoegd";
    }
}
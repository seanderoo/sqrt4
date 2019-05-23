package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.model.Vacature;
import sqrt4.mijninzet.repository.VacatureRepository;


@Controller
public class VacatureController extends AbstractController{

    @Autowired
    VacatureRepository vacrepo;

    @GetMapping("/admin/nieuwe-vacature")
    public String nieuweVacature(){
    return "/nieuwe-vacature";
    }

    @PostMapping("/admin/nieuwe-vacature")
    public String nieuweGebruiker(@ModelAttribute("vacature") Vacature vacature) {
        vacature.setVacatureNaam(vacature.getVacatureNaam());
        vacature.setOmschrijving(vacature.getOmschrijving());
        vacature.setAantalUren(vacature.getAantalUren());
        vacature.setEisen(vacature.getVacatureNaam());
        vacrepo.save(vacature);
        return "/vacature-toegevoegd";
    }
}
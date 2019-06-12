package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Vakdagdeel;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.VakdagdeelRespository;

@Controller
public class VakController {

    @Autowired
    VakRepository vakRepository;

    @Autowired
    VakdagdeelRespository vakdagdeelRespository;

    @GetMapping("/manager/vak-aanmaken")
    public String vakAanmaken() {
        return "manager/vak-aanmaken";
    }

    @PostMapping("/manager/vak-toegevoegd")
    public String vakToegevoegd(@ModelAttribute Vak vak){
        vakRepository.save(vak);
        vak.setVakdagdelen(vak.aantalDagdelenBerekenen());
        for (Vakdagdeel vakdagdeel :vak.getVakdagdelen()) {
            vakdagdeelRespository.save(vakdagdeel);
        }
        return "manager/vak-toegevoegd";
    }

    @GetMapping("/manager/vak-rooster")
    public String vakRoosterMaken() {
        return "manager/vak-rooster";
    }
}

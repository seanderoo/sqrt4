package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sqrt4.mijninzet.model.Dagdeel;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.DagdeelRespository;
import sqrt4.mijninzet.repository.VakRepository;

@Controller
public class VakController {

    @Autowired
    VakRepository vakRepository;

    @Autowired
    DagdeelRespository dagdeelRespository;

    @GetMapping("/manager/vak-aanmaken")
    public String vakAanmaken() {
        return "/vak-aanmaken";
    }

    @PostMapping("/manager/vak-toegevoegd")
    public String vakToegevoegd(@ModelAttribute Vak vak){
        vakRepository.save(vak);
        vak.setDagdelen(vak.aantalDagdelenBerekenen());
        for (Dagdeel dagdeel:vak.getDagdelen()) {
            dagdeelRespository.save(dagdeel);
        }
        return "/vak-toegevoegd";}

    @GetMapping("/manager/vak-rooster")
    public String vakRoosterMaken() {
        return "/vak-rooster";
    }
}

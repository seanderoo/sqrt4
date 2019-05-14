package sqrt4.mijninzet.model;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;

@Entity
public class Voorkeur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Vak vak;
    private int voorkeur;


    public Voorkeur(int id, Vak vak, int voorkeur){
        this.id = id;
        this.vak = vak;
        this.voorkeur = voorkeur;
    }

    @GetMapping("/voorkeuren")
    public String voorkeuren(@RequestParam(value= "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "voorkeuren"; // voorkeuren.html
    }
}

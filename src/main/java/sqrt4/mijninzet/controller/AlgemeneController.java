package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Voorkeur;
import sqrt4.mijninzet.repository.VoorkeurenRepository;

@RestController
@RequestMapping("/ajax/voorkeuren")
public class AlgemeneController {

    @Autowired
    private VoorkeurenRepository voorkeurenRepository;

    @RequestMapping(value="/{user}/{vak}", method = RequestMethod.POST)
    public String testRest(@PathVariable User user, @PathVariable Vak vak) {
        System.out.println("******* Hij doet het!!! User = " + user.getId() + ", vak = " + vak.getVakId());
        Voorkeur voorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vak, user);
        if (voorkeur == null) {
            System.out.println("deze gebruiker heeft nog geen voorkeur opgegeven voor dit vak :{");
        } else {
            System.out.println("dit is het voorkeur voor dit vak van deze gebruiker " + voorkeur.getVoorkeurGebruiker());
        }
        return "hij doet het!";
    }



}

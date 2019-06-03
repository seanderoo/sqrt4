package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.VoorkeurenRepository;

@RestController
@RequestMapping("/ajax")
public class AlgemeneController {

    @Autowired
    private VoorkeurenRepository voorkeurenRepository;

    @RequestMapping(value = "/voorkeuren/{user}/{vak}", method = RequestMethod.POST)
    public Integer testRest(@PathVariable User user, @PathVariable Vak vak) {
        Integer preference = 0;
        try {
            Integer databaseVoorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vak, user).getVoorkeurGebruiker();
            if (databaseVoorkeur == null) {
                return 0;
            } else {
                return databaseVoorkeur;
            }
        } catch (NullPointerException e) {
        }
        return preference;
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.POST)
    public String testRest2(@PathVariable User user) {
        return "";
    }

}

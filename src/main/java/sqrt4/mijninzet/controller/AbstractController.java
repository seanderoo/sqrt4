package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.repository.UserRepository;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.WeekRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    VakRepository vakRepository;
    @Autowired
    WeekRepository weekRepo;



    User voegActiveUserToe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return userRepo.findByUsername(userName);
    }

    public List<Vak> vakkenLijstZonder(String vakNaam) {
        List<Vak> vakkenZonder = vakRepository.findAll();
        Vak verwijderVak = vakRepository.findByVakNaam(vakNaam);
        vakkenZonder.remove(verwijderVak);
        Collections.sort(vakkenZonder);
        return vakkenZonder;
    }

    public List<User> beschikbareDocentenPerDagdeel() {
        List<User> docenten = userRepo.findAll();
        List<User> beschikbareDocenten = new ArrayList<>();
        for (User user : docenten) {
            Week nieuw = weekRepo.findByUser(user);
            if ( nieuw.getMaandag().getOchtend().getBeschikbaar() ) {
                beschikbareDocenten.add(user);
            }

        }
        return beschikbareDocenten;
    }
}

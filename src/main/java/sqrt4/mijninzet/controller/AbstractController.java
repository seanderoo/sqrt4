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

    List<Week> weken = new ArrayList<>();

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

    public List<User> docentenBeschikbaarheid() {
        List<User> docenten = haalBeschikbaarhedenOp(weken);
        return docenten;
    }

    private List<User> haalBeschikbaarhedenOp(List<Week> wekenlijst) {

        weken = weekRepo.findAllByJaarNummerAndWeekNummer(0, 0);
        List<User> users = new ArrayList<>();
        for (Week week : wekenlijst) {
            User user = userRepo.findByWeek(week);
            users.add(user);
        }
        return users;
    }
}

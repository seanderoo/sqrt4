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

    public void addWeek(User user) {
        Week algemeneWeek = new Week();
        algemeneWeek.setUser(user);
        weekRepo.save(algemeneWeek);
    }

    //wordt gebruikt om "Geen les" uit de lijst met vakken te halen (Karin)
    public List<Vak> vakkenLijstZonder(String vakNaam) {
        List<Vak> vakkenZonder = vakRepository.findAll();
        Vak verwijderVak = vakRepository.findByVakNaam(vakNaam);
        vakkenZonder.remove(verwijderVak);
        Collections.sort(vakkenZonder);
        return vakkenZonder;
    }

    public List<User> beschikbareDocentenMetWeek() {
        List<User> docenten = userRepo.findAllByRolesContaining("DOCENT");
        List<User> beschikbareDocentenMetWeek = new ArrayList<>();

        for (User user : docenten) {

            beschikbareDocentenMetWeek.add(user);
        }
        return docenten;
    }

    public List<User> getDocentenInOchtend(String dag) {
        List<User> beschikbareDocenten = new ArrayList<>();
        for (User user : beschikbareDocentenMetWeek()) {
            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
            if (nieuw.getDag(dag).getOchtend().getBeschikbaar() ) {
                beschikbareDocenten.add(user);
            }
        }
        return beschikbareDocenten;
    }

    public List<User> getDocentenInMiddag(String dag) {
        List<User> beschikbareDocenten = new ArrayList<>();
        for (User user : beschikbareDocentenMetWeek()) {
            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
            if (nieuw.getDag(dag).getMiddag().getBeschikbaar() ) {
                beschikbareDocenten.add(user);
            }
        }
        return beschikbareDocenten;
    }

    public List<User> getDocentenInAvond(String dag) {
        List<User> beschikbareDocenten = new ArrayList<>();
        for (User user : beschikbareDocentenMetWeek()) {
            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
            if ( nieuw.getDag(dag).getAvond().getBeschikbaar() ) {
                beschikbareDocenten.add(user);
            }
        }
        return beschikbareDocenten;
    }

    //wordt gebruikt om "Kies docent" uit de lijst met gebruikers te halen (Karin)
    public List<User> gebruikersLijstZonder(String userName) {
        List<User> gebruikersZonder = userRepo.findAll();
        User verwijderUser = userRepo.findByUsername(userName);
        gebruikersZonder.remove(verwijderUser);
        return gebruikersZonder;
    }
}

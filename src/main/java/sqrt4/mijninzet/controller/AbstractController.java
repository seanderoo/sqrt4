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

    public List<User> beschikbareDocentenPerDagdeel(String dag, String dagdeel) {
        List<User> docenten = userRepo.findAllByRolesContaining("DOCENT");
        List<User> beschikbareDocenten = new ArrayList<>();
        List<User> beschikbareDocentenMetWeek = new ArrayList<>();
        for (User user : docenten) {
            if ( user.getWeek() != null ) {
                beschikbareDocentenMetWeek.add(user);
            }
        }

        switch (dag) {
            case "maandag":
                switch (dagdeel) {
                    case "ochtend":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getMaandag().getOchtend().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "middag":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getMaandag().getMiddag().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "avond":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getMaandag().getAvond().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                }
                break;

            case "dinsdag":
                switch (dagdeel) {
                    case "ochtend":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getDinsdag().getOchtend().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "middag":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getDinsdag().getMiddag().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "avond":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getDinsdag().getAvond().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                }
                break;

            case "woensdag":
                switch (dagdeel) {
                    case "ochtend":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getWoensdag().getOchtend().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "middag":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getWoensdag().getMiddag().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "avond":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getWoensdag().getAvond().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                }
                break;

            case "donderdag":
                switch (dagdeel) {
                    case "ochtend":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getDonderdag().getOchtend().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "middag":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getDonderdag().getMiddag().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "avond":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getDonderdag().getAvond().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                }
                break;

            case "vrijdag":
                switch (dagdeel) {
                    case "ochtend":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getVrijdag().getOchtend().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "middag":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getVrijdag().getMiddag().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                    case "avond":
                        for (User user : beschikbareDocentenMetWeek) {
                            Week nieuw = weekRepo.findByCohortIsNullAndUser(user);
                            if ( nieuw.getVrijdag().getAvond().getBeschikbaar() ) {
                                beschikbareDocenten.add(user);
                            }
                        }
                        break;
                }
                break;
        }
        return beschikbareDocenten;
    }
}

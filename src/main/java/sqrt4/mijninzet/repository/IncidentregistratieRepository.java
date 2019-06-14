package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Incident;
import sqrt4.mijninzet.model.User;

import java.time.LocalDate;
import java.util.List;


public interface IncidentregistratieRepository extends JpaRepository<Incident, Long> {

    List<Incident> findAllByUser(User user);

    Incident findByDatumAndUser(LocalDate date, User User);

    Incident existsByDatum(LocalDate date);

    Incident findIncidentById(Long value);

    Incident deleteByDatum(LocalDate date);

    List<Incident> findAllByUserAndStatusNotContaining(User user, String value);

    List<Incident> findAllByUserAndStatusContaining(User user, String value);

    List<Incident> findAllByStatusIsContaining(String status);

    List<Incident> findAllByStatusIsNotContaining(String status);

    Incident findIncidentByUser(User user);

}

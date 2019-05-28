package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Incident;
import sqrt4.mijninzet.model.User;

import java.time.LocalDate;
import java.util.List;


public interface IncidentregistratieRepository extends JpaRepository<Incident, Long> {

    List<Incident> findAllByUser(User user);

    List<Incident> deleteByDatum(LocalDate date);

}

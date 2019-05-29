package sqrt4.mijninzet.repository;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Incident;
import sqrt4.mijninzet.model.User;

import java.time.LocalDate;
import java.util.List;


public interface IncidentregistratieRepository extends JpaRepository<Incident, LocalDate> {

    List<Incident> findAllByUser(User user);

    Incident findByDatum(LocalDate date);

    Incident deleteByDatum(LocalDate date);

}

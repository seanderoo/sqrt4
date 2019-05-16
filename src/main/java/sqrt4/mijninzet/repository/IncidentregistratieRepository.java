package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Dag;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;

import java.time.LocalDate;

public interface IncidentregistratieRepository extends JpaRepository<Dag, Integer> {

    Dag findDagByDatum(LocalDate date);

    Week findByJaarNummerAndWeekNummer(int jaarNr, int weekNr);
}

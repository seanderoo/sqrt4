package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Integer> {

    Week findByJaarNummerAndWeekNummer(int jaarNr, int weekNr);

    Week findByWeekNummerAndUser(int weekNr, User user);

    List<Week> findWeeksByCohortId(int cohortId);

    Week findByWeekNummerAndCohort(int weekNummer, Cohort cohort);

    List<Week> findAllByCohort(Cohort cohort);

    Week findByWeekNummer(int weeknummer);

    Week findById(int weekid);
}

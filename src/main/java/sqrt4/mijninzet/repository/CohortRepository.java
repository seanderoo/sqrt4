package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;

import java.time.LocalDate;
import java.util.List;

public interface CohortRepository extends JpaRepository<Cohort, Integer> {

    Cohort findByCohortNaam(String cohortnaam);
    List<Cohort> findAllByStartJaarIsGreaterThanEqual(int jaar);
    Cohort findById(int id);


}

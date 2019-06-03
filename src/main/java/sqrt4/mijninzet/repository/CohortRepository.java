package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;

import java.time.LocalDate;

public interface CohortRepository extends JpaRepository<Cohort, Integer> {

}

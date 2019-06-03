package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, Integer> {
}

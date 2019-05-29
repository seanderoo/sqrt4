package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;

public interface AlgemeneBeschikbaarheidRepository extends JpaRepository<Cohort, String> {
}

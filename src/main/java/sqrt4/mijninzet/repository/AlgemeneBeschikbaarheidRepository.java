package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Semester;

public interface AlgemeneBeschikbaarheidRepository extends JpaRepository<Semester, String> {
}

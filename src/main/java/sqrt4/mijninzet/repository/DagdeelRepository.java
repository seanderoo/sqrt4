package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Dag;
import sqrt4.mijninzet.model.Beschikbaarheid.Dagdeel;
import sqrt4.mijninzet.model.Vak;

import java.util.List;

public interface DagdeelRepository extends JpaRepository<Dagdeel, Integer> {

}

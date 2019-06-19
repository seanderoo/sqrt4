package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Dagdeel;

public interface DagdeelRepository extends JpaRepository<Dagdeel, Integer> {

    int countByVakVakIdAndDag_Id(int vakId, int dagId);

}

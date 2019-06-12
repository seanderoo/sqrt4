package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Dag;
import sqrt4.mijninzet.model.Beschikbaarheid.Dagdeel;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.Vak;

import java.util.ArrayList;
import java.util.List;

public interface DagRepository extends JpaRepository<Dag, Integer> {

    List<Dag> findByWeek_Id(int weekId);

}

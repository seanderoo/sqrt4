package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Dag;
import sqrt4.mijninzet.model.Beschikbaarheid.Semester;
import sqrt4.mijninzet.model.User;

import java.util.List;

public interface AlgemeneBeschikbaarheidRepository extends JpaRepository<Semester, String> {

    List<Semester> findAllByUser(User user);

    Semester findBySemesterNaamAndUser(String naam, User user);

}

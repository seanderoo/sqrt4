package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Beschikbaarheid.Semester;

import java.util.ArrayList;
import java.util.List;

public interface AlgemeneBeschikbaarheidRepository extends JpaRepository<Semester, String> {

}

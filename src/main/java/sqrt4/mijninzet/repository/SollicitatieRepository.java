package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqrt4.mijninzet.model.Sollicitatie;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vacature;

@Repository
public interface SollicitatieRepository extends JpaRepository<Sollicitatie, Integer> {

    Sollicitatie findByUserAndVacature(User user, Vacature vacature);
}

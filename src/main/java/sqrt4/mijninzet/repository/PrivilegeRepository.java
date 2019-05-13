package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.Users.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}

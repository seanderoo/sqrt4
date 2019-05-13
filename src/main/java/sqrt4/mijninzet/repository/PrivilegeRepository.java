package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.users.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}

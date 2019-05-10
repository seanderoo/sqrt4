package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqrt4.mijninzet.model.users.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

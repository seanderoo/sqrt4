package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqrt4.mijninzet.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

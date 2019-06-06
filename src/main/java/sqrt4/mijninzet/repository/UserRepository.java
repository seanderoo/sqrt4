package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqrt4.mijninzet.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findUserById(Long userId);
    List<User> findAllByRolesContaining(String role);
}

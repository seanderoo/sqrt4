package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqrt4.mijninzet.model.Users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

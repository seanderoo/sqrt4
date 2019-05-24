package sqrt4.mijninzet.repository;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import sqrt4.mijninzet.model.User;
        import sqrt4.mijninzet.model.Voorkeur;
        import javax.transaction.Transactional;

@Repository
public interface VoorkeurenRepository extends JpaRepository<Voorkeur, Integer> {

    @Transactional
    void deleteByVak_VakIdAndUser(int vakId, User user);

}
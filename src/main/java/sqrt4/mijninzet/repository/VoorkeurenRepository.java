package sqrt4.mijninzet.repository;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import sqrt4.mijninzet.model.User;
        import sqrt4.mijninzet.model.Vak;
        import sqrt4.mijninzet.model.Voorkeur;
        import javax.transaction.Transactional;
        import java.util.List;

@Repository
public interface VoorkeurenRepository extends JpaRepository<Voorkeur, Integer> {

    @Transactional
    void deleteByVak_VakIdAndUser(int vakId, User user);

    Voorkeur findVoorkeurByVakAndUser(Vak vak, User user);

    Voorkeur findVoorkeurByUser(User user);

    List<Voorkeur> findAllByUser(User user);
}
package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqrt4.mijninzet.model.Vak;

import java.util.List;

@Repository
public interface VakRepository extends JpaRepository<Vak, Integer> {

    Vak findByVakNaam(String vaknaam);
    Vak findById(int Id);
    void deleteByVakId(int vakId);
    Vak findVakByVakId(int vakId);
}
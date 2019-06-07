package sqrt4.mijninzet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqrt4.mijninzet.model.Vakdagdeel;

@Repository
public interface VakdagdeelRespository extends JpaRepository<Vakdagdeel, Integer> {

    Vakdagdeel findById(int id);

}

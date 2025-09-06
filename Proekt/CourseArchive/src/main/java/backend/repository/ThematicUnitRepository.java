package backend.repository;

import backend.model.ThematicUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThematicUnitRepository extends JpaRepository<ThematicUnit, Long> {
    List<ThematicUnit> findByCourseId(Long courseId);
}
package backend.service;

import backend.dto.ThematicUnitCreateDTO;
import backend.dto.ThematicUnitDTO;

import java.util.List;

public interface ThematicUnitService {

    ThematicUnitDTO createThematicUnit(ThematicUnitCreateDTO dto);
    ThematicUnitDTO getThematicUnit(Long id);
    List<ThematicUnitDTO> getByCourse(Long courseId);
    ThematicUnitDTO updateThematicUnit(Long id, ThematicUnitCreateDTO dto);
    void deleteThematicUnit(Long id);
}
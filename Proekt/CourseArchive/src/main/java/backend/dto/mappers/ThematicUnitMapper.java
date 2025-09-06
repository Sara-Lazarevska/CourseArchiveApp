package backend.dto.mappers;

import backend.dto.ThematicUnitCreateDTO;
import backend.dto.ThematicUnitDTO;
import backend.model.ThematicUnit;

public class ThematicUnitMapper {

    public static ThematicUnitDTO toDto(ThematicUnit unit) {
        return new ThematicUnitDTO(
                unit.getId(),
                unit.getTitle(),
                unit.getDescription(),
                unit.getType(),
                unit.getCourse().getId()
        );
    }

    public static ThematicUnit toEntity(ThematicUnitCreateDTO dto) {
        ThematicUnit unit = new ThematicUnit();
        unit.setTitle(dto.title());
        unit.setDescription(dto.description());
        unit.setType(dto.type());
        return unit;
    }
}

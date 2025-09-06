package backend.dto;

import backend.model.UnitType;

public record ThematicUnitCreateDTO(
        String title,
        String description,
        UnitType type,
        Long courseId
) {}

package backend.dto;

import backend.model.UnitType;

public record ThematicUnitDTO(
        Long id,
        String title,
        String description,
        UnitType type,
        Long courseId
) {}


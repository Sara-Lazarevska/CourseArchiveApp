package backend.dto;

import backend.model.ResourceType;

public record ResourceDTO(
        Long id,
        String filename,
        String fileType,
        String url,
        ResourceType resourceType,
        Long thematicUnitId
) {}


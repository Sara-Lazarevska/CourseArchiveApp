package backend.dto;

import backend.model.ResourceType;

public record ResourceCreateDTO(
        String filename,
        String fileType,
        String url,
        ResourceType resourceType,
        Long thematicUnitId
) {}


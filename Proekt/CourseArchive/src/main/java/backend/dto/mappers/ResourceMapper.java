package backend.dto.mappers;

import backend.dto.ResourceCreateDTO;
import backend.dto.ResourceDTO;
import backend.model.Resource;

public class ResourceMapper {

    public static ResourceDTO toDto(Resource resource) {
        return new ResourceDTO(
                resource.getId(),
                resource.getFilename(),
                resource.getFileType(),
                resource.getUrl(),
                resource.getResourceType(),
                resource.getThematicUnit().getId()
        );
    }

    public static Resource toEntity(ResourceCreateDTO dto) {
        Resource resource = new Resource();
        resource.setFilename(dto.filename());
        resource.setFileType(dto.fileType());
        resource.setUrl(dto.url());
        resource.setResourceType(dto.resourceType());
        return resource;
    }
}
package backend.service;

import backend.dto.ResourceCreateDTO;
import backend.dto.ResourceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResourceService {

    ResourceDTO createResource(ResourceCreateDTO dto);

    ResourceDTO getResource(Long id);

    List<ResourceDTO> getByThematicUnit(Long thematicUnitId);

    ResourceDTO updateResource(Long id, ResourceCreateDTO dto);

    void deleteResource(Long id);

    void uploadFile(Long resourceId, MultipartFile file);
}

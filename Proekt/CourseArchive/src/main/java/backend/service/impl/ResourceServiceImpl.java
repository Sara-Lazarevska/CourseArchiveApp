package backend.service.impl;

import backend.dto.ResourceCreateDTO;
import backend.dto.ResourceDTO;
import backend.dto.mappers.ResourceMapper;
import backend.model.Resource;
import backend.model.ThematicUnit;
import backend.repository.ResourceRepository;
import backend.repository.ThematicUnitRepository;
import backend.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ThematicUnitRepository thematicUnitRepository;

    private final String uploadDir = "uploads/";

    @Override
    public ResourceDTO createResource(ResourceCreateDTO dto) {
        ThematicUnit unit = thematicUnitRepository.findById(dto.thematicUnitId())
                .orElseThrow(() -> new RuntimeException("Thematic unit not found"));

        Resource resource = ResourceMapper.toEntity(dto);
        resource.setThematicUnit(unit);

        resourceRepository.save(resource);
        return ResourceMapper.toDto(resource);
    }

    @Override
    public ResourceDTO getResource(Long id) {
        return resourceRepository.findById(id)
                .map(ResourceMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    @Override
    public List<ResourceDTO> getByThematicUnit(Long thematicUnitId) {
        return resourceRepository.findByThematicUnitId(thematicUnitId)
                .stream()
                .map(ResourceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceDTO updateResource(Long id, ResourceCreateDTO dto) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        resource.setFilename(dto.filename());
        resource.setFileType(dto.fileType());
        resource.setResourceType(dto.resourceType());
        resource.setUrl(dto.url());

        resourceRepository.save(resource);
        return ResourceMapper.toDto(resource);
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

//    @Override
//    public void uploadFile(Long resourceId, String filePath) {
//
//    }

    @Override
    public void uploadFile(Long resourceId, MultipartFile file) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        try {
            File destDir = new File(uploadDir);
            if (!destDir.exists()) destDir.mkdirs();

            String destFilename = resourceId + "_" + file.getOriginalFilename();
            File dest = new File(destDir, destFilename);

            Files.copy(file.getInputStream(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Сетирање на атрибутите
            resource.setFilename(file.getOriginalFilename());
            resource.setFileType(file.getContentType());
            resource.setUrl(dest.getAbsolutePath()); // можеш подоцна да смениш на URL за frontend
            resourceRepository.save(resource);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}

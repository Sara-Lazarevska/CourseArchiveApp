package backend.controller;

import backend.dto.ResourceCreateDTO;
import backend.dto.ResourceDTO;
import backend.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    // CREATE
    @PostMapping
    public ResponseEntity<ResourceDTO> create(@RequestBody ResourceCreateDTO dto) {
        return ResponseEntity.ok(resourceService.createResource(dto));
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(resourceService.getResource(id));
    }

    @GetMapping("/by-thematic-unit/{thematicUnitId}")
    public ResponseEntity<List<ResourceDTO>> getByThematicUnit(@PathVariable Long thematicUnitId) {
        return ResponseEntity.ok(resourceService.getByThematicUnit(thematicUnitId));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ResourceDTO> update(@PathVariable Long id, @RequestBody ResourceCreateDTO dto) {
        return ResponseEntity.ok(resourceService.updateResource(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }

    // UPLOAD FILE
    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        resourceService.uploadFile(id, file);
        return ResponseEntity.ok("File uploaded successfully!");
    }
}

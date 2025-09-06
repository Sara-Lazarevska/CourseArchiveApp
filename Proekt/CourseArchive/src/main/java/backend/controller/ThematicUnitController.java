package backend.controller;

import backend.dto.ThematicUnitCreateDTO;
import backend.dto.ThematicUnitDTO;
import backend.service.ThematicUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thematic-units")
@RequiredArgsConstructor
public class ThematicUnitController {

    private final ThematicUnitService thematicUnitService;

    // CREATE
    @PostMapping
    public ResponseEntity<ThematicUnitDTO> create(@RequestBody ThematicUnitCreateDTO dto) {
        return ResponseEntity.ok(thematicUnitService.createThematicUnit(dto));
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<ThematicUnitDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(thematicUnitService.getThematicUnit(id));
    }

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<List<ThematicUnitDTO>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(thematicUnitService.getByCourse(courseId));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ThematicUnitDTO> update(@PathVariable Long id, @RequestBody ThematicUnitCreateDTO dto) {
        return ResponseEntity.ok(thematicUnitService.updateThematicUnit(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        thematicUnitService.deleteThematicUnit(id);
        return ResponseEntity.noContent().build();
    }
}

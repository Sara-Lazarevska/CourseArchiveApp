package backend.controller;

import backend.dto.CourseCreateDTO;
import backend.dto.CourseDTO;
import backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // CREATE
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseCreateDTO dto) {
        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourse(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseCreateDTO dto) {
        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // EXPORT CSV
    @GetMapping("/export")
    public ResponseEntity<String> exportCoursesToCsv() {
        String filePath = "courses_export.csv";
        try {
            courseService.exportCoursesToCsv(filePath);
            return ResponseEntity.ok("Courses exported to " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to export CSV: " + e.getMessage());
        }
    }

    // IMPORT CSV
    @PostMapping("/import")
    public ResponseEntity<String> importCoursesFromCsv(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("courses_import", ".csv");
            file.transferTo(tempFile);

            courseService.importCoursesFromCsv(tempFile.getAbsolutePath());

            tempFile.delete();

            return ResponseEntity.ok("Courses imported successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to import CSV: " + e.getMessage());
        }
    }
}

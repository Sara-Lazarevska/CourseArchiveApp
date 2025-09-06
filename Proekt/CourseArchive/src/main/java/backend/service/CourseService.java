package backend.service;

import backend.dto.CourseCreateDTO;
import backend.dto.CourseDTO;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseCreateDTO dto);
    CourseDTO getCourse(Long id);
    List<CourseDTO> getAllCourses();
    CourseDTO updateCourse(Long id, CourseCreateDTO dto);
    void deleteCourse(Long id);

    // CSV import/export
    void exportCoursesToCsv(String filePath) throws IOException;
    void importCoursesFromCsv(String filePath) throws IOException;
}
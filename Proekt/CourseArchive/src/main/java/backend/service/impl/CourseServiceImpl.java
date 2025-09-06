package backend.service.impl;

import backend.csv.CourseCsvUtil;
import backend.dto.CourseCreateDTO;
import backend.dto.CourseDTO;
import backend.dto.mappers.CourseMapper;
import backend.model.Course;
import backend.repository.CourseRepository;
import backend.service.CourseService;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseDTO createCourse(CourseCreateDTO dto) {
        Course course = CourseMapper.toEntity(dto);
        courseRepository.save(course);
        return CourseMapper.toDto(course);
    }

    @Override
    public CourseDTO getCourse(Long id) {
        return courseRepository.findById(id)
                .map(CourseMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseCreateDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setCode(dto.code());
        course.setName(dto.name());
        course.setSemester(dto.semester());
        course.setYear(dto.year());
        courseRepository.save(course);
        return CourseMapper.toDto(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void exportCoursesToCsv(String filePath) throws IOException {
        List<CourseDTO> courses = getAllCourses();
        CourseCsvUtil.exportCoursesToCsv(courses, filePath);
    }

    @Override
    public void importCoursesFromCsv(String filePath) throws IOException {
        try {
            List<CourseCreateDTO> courses = CourseCsvUtil.importCoursesFromCsv(filePath);
            for (CourseCreateDTO dto : courses) {
                createCourse(dto);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException("CSV validation error", e);
        }
    }
}
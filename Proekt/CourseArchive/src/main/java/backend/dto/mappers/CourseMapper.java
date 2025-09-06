package backend.dto.mappers;

import backend.dto.CourseCreateDTO;
import backend.dto.CourseDTO;
import backend.model.Course;

import java.util.ArrayList;

public class CourseMapper {

    public static CourseDTO toDto(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getCode(),
                course.getName(),
                course.getSemester(),
                course.getYear(),
                course.getTeachers().stream().map(t -> t.getId()).toList(),
                course.getAssistants().stream().map(a -> a.getId()).toList(),
                course.getStudents().stream().map(s -> s.getId()).toList()
        );
    }

    public static Course toEntity(CourseCreateDTO dto) {
        Course course = new Course();
        course.setCode(dto.code());
        course.setName(dto.name());
        course.setSemester(dto.semester());
        course.setYear(dto.year());
        course.setTeachers(new ArrayList<>());   // ќе ги сетираш после преку сервис
        course.setAssistants(new ArrayList<>());
        course.setStudents(new ArrayList<>());
        return course;
    }
}
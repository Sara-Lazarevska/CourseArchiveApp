package backend.dto;

import java.util.List;

public record CourseCreateDTO(
        String code,
        String name,
        int semester,
        int year,
        List<Long> teacherIds,
        List<Long> assistantIds,
        List<Long> studentIds
) {}


package backend.service.impl;


import backend.dto.ThematicUnitCreateDTO;
import backend.dto.ThematicUnitDTO;
import backend.dto.mappers.ThematicUnitMapper;
import backend.model.Course;
import backend.model.ThematicUnit;
import backend.repository.CourseRepository;
import backend.repository.ThematicUnitRepository;
import backend.service.ThematicUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThematicUnitServiceImpl implements ThematicUnitService {

    private final ThematicUnitRepository thematicUnitRepository;
    private final CourseRepository courseRepository;

    @Override
    public ThematicUnitDTO createThematicUnit(ThematicUnitCreateDTO dto) {
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        ThematicUnit unit = ThematicUnitMapper.toEntity(dto);
        unit.setCourse(course);

        thematicUnitRepository.save(unit);
        return ThematicUnitMapper.toDto(unit);
    }

    @Override
    public ThematicUnitDTO getThematicUnit(Long id) {
        return thematicUnitRepository.findById(id)
                .map(ThematicUnitMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Thematic unit not found"));
    }

    @Override
    public List<ThematicUnitDTO> getByCourse(Long courseId) {
        return thematicUnitRepository.findByCourseId(courseId)
                .stream()
                .map(ThematicUnitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ThematicUnitDTO updateThematicUnit(Long id, ThematicUnitCreateDTO dto) {
        ThematicUnit unit = thematicUnitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thematic unit not found"));

        unit.setTitle(dto.title());
        unit.setDescription(dto.description());
        unit.setType(dto.type());
        thematicUnitRepository.save(unit);

        return ThematicUnitMapper.toDto(unit);
    }

    @Override
    public void deleteThematicUnit(Long id) {
        thematicUnitRepository.deleteById(id);
    }
}

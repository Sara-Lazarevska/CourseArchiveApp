package backend.init;
import backend.dto.CourseCreateDTO;
import backend.dto.ResourceCreateDTO;
import backend.dto.ThematicUnitCreateDTO;
import backend.dto.UserCreateDTO;
import backend.model.ResourceType;
import backend.model.Role;
import backend.model.UnitType;
import backend.service.CourseService;
import backend.service.ResourceService;
import backend.service.ThematicUnitService;
import backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final CourseService courseService;
    private final ThematicUnitService thematicUnitService;
    private final ResourceService resourceService;

    @Override
    public void run(String... args) throws Exception {

        // ---- USERS ----
        UserCreateDTO student1 = new UserCreateDTO("Ana", "Petrova", "ana@mail.com", "password123", Role.STUDENT, "12345", "CS");
        UserCreateDTO teacher1 = new UserCreateDTO("Dragan", "Ivanov", "dragan@mail.com", "password123", Role.TEACHER, null, null);

        userService.createUser(student1);
        userService.createUser(teacher1);

        // ---- COURSES ----
        CourseCreateDTO course1 = new CourseCreateDTO("CS101", "Introduction to CS", 1, 2025,
                null, null, null);
        var savedCourse = courseService.createCourse(course1);

        // ---- THEMATIC UNITS ----
        ThematicUnitCreateDTO unit1 = new ThematicUnitCreateDTO("Basics of Programming", "Learn variables, loops, conditions", UnitType.LECTURE, savedCourse.id());
        var savedUnit = thematicUnitService.createThematicUnit(unit1);

        // ---- RESOURCES ----
        ResourceCreateDTO res1 = new ResourceCreateDTO("Variables Example", "pdf", "http://example.com/variables.pdf", ResourceType.EXAMPLE, savedUnit.id());
        ResourceCreateDTO res2 = new ResourceCreateDTO("Homework 1", "pdf", "http://example.com/homework1.pdf", ResourceType.SOLUTION, savedUnit.id());

        resourceService.createResource(res1);
        resourceService.createResource(res2);

        System.out.println("Test data initialized!");
    }
}


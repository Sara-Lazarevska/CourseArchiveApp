package backend.csv;

import backend.dto.CourseCreateDTO;
import backend.dto.CourseDTO;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseCsvUtil {

    // Export Courses -> CSV
    public static void exportCoursesToCsv(List<CourseDTO> courses, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"ID", "Code", "Name", "Semester", "Year"});

            for (CourseDTO course : courses) {
                writer.writeNext(new String[]{
                        course.id() != null ? course.id().toString() : "",
                        course.code(),
                        course.name(),
                        String.valueOf(course.semester()),
                        String.valueOf(course.year())
                });
            }
        }
    }

    // Import Courses <- CSV
    public static List<CourseCreateDTO> importCoursesFromCsv(String filePath) throws IOException, CsvValidationException {
        List<CourseCreateDTO> courses = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            boolean isFirst = true;

            while ((line = reader.readNext()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue; // skip header
                }

                courses.add(new CourseCreateDTO(
                        line[1], // code
                        line[2], // name
                        Integer.parseInt(line[3]), // semester
                        Integer.parseInt(line[4]), // year
                        Collections.emptyList(), // teacherIds
                        Collections.emptyList(), // assistantIds
                        Collections.emptyList()  // studentIds
                ));
            }
        }

        return courses;
    }
}


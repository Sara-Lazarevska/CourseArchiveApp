package backend.csv;
import backend.dto.UserCreateDTO;
import backend.dto.UserDTO;
import backend.model.Role;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserCsvUtil {

    // Export Users -> CSV
    public static void exportUsersToCsv(List<UserDTO> users, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Header
            writer.writeNext(new String[]{"ID", "FirstName", "LastName", "Email", "Role", "IndexNumber", "StudyProgram"});

            for (UserDTO user : users) {
                writer.writeNext(new String[]{
                        user.id() != null ? user.id().toString() : "",
                        user.firstName(),
                        user.lastName(),
                        user.email(),
                        user.role().name(),
                        user.indexNumber() != null ? user.indexNumber() : "",
                        user.studyProgram() != null ? user.studyProgram() : ""
                });
            }
        }
    }

    // Import Users <- CSV
    public static List<UserCreateDTO> importUsersFromCsv(String filePath) throws IOException, CsvValidationException {
        List<UserCreateDTO> users = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            boolean isFirst = true;

            while ((line = reader.readNext()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                users.add(new UserCreateDTO(
                        line[1],
                        line[2],
                        line[3],
                        "defaultPass123",
                        Role.valueOf(line[4]),
                        line[5].isEmpty() ? null : line[5],
                        line[6].isEmpty() ? null : line[6]
                ));
            }
        }

        return users;
    }

}


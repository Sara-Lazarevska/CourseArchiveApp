package backend.service;

import backend.dto.UserCreateDTO;
import backend.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserDTO createUser(UserCreateDTO dto);
    UserDTO getUser(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserCreateDTO dto);
    void deleteUser(Long id);

    // CSV import/export
    void exportUsersToCsv(String filePath) throws IOException;
    void importUsersFromCsv(String filePath) throws IOException;
}
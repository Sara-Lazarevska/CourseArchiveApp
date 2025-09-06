package backend.service.impl;

import backend.csv.UserCsvUtil;
import backend.dto.UserCreateDTO;
import backend.dto.UserDTO;
import backend.dto.mappers.UserMapper;
import backend.model.User;
import backend.repository.UserRepository;
import backend.service.UserService;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserCreateDTO dto) {
        User user = UserMapper.toEntity(dto);
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public UserDTO getUser(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserCreateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setIndexNumber(dto.indexNumber());
        user.setStudyProgram(dto.studyProgram());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void exportUsersToCsv(String filePath) throws IOException {
        List<UserDTO> users = getAllUsers();
        UserCsvUtil.exportUsersToCsv(users, filePath);
    }

    @Override
    public void importUsersFromCsv(String filePath) throws IOException {
        try {
            List<UserCreateDTO> users = UserCsvUtil.importUsersFromCsv(filePath);
            for (UserCreateDTO dto : users) {
                createUser(dto);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException("CSV validation error", e);
        }
    }
}
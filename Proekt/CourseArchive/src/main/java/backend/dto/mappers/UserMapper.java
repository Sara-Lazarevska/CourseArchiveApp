package backend.dto.mappers;


import backend.dto.UserCreateDTO;
import backend.dto.UserDTO;
import backend.model.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getIndexNumber(),
                user.getStudyProgram()
        );
    }

    public static User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setIndexNumber(dto.indexNumber());
        user.setStudyProgram(dto.studyProgram());
        return user;
    }
}

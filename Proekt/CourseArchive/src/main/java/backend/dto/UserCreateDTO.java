package backend.dto;

import backend.model.Role;

public record UserCreateDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        Role role,
        String indexNumber,
        String studyProgram
) {}


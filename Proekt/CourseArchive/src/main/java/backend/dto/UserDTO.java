package backend.dto;

import backend.model.Role;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role,
        String indexNumber,
        String studyProgram
) {}


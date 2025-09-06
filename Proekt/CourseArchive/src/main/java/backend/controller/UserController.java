package backend.controller;


import backend.dto.UserCreateDTO;
import backend.dto.UserDTO;
import backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserCreateDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // EXPORT CSV
    @GetMapping("/export")
    public ResponseEntity<String> exportUsersToCsv() {
        String filePath = "users_export.csv"; // можеш да го смениш или да го прими како параметар
        try {
            userService.exportUsersToCsv(filePath);
            return ResponseEntity.ok("Users exported to " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to export CSV: " + e.getMessage());
        }
    }

    // IMPORT CSV
    @PostMapping("/import")
    public ResponseEntity<String> importUsersFromCsv(@RequestParam("file") MultipartFile file) {
        try {
            // Save uploaded file temporarily
            File tempFile = File.createTempFile("users_import", ".csv");
            file.transferTo(tempFile);

            userService.importUsersFromCsv(tempFile.getAbsolutePath());

            // Delete temp file
            tempFile.delete();

            return ResponseEntity.ok("Users imported successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to import CSV: " + e.getMessage());
        }
    }
}

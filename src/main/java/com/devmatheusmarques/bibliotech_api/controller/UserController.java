package com.devmatheusmarques.bibliotech_api.controller;

import com.devmatheusmarques.bibliotech_api.dto.UserEditDTO;
import com.devmatheusmarques.bibliotech_api.dto.UserResponseDTO;
import com.devmatheusmarques.bibliotech_api.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<Void> editUser(@PathVariable Long id, @Valid @RequestBody UserEditDTO userEditDTO) {
        if (userEditDTO.getPassword() != null && !userEditDTO.getPassword().trim().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userEditDTO.getPassword());
            userEditDTO.setPassword(encryptedPassword);
        } else {
            userEditDTO.setPassword(null);
        }

        userService.userEdit(id, userEditDTO);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.userDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> response = userService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "login")
    public ResponseEntity<UserResponseDTO> findByLogin(@RequestParam @NotBlank String login) {
        try {
            UserResponseDTO user = userService.findByLogin(login);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

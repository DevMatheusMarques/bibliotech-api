package com.devmatheusmarques.bibliotech_api.controller;

import com.devmatheusmarques.bibliotech_api.dto.AuthorRequestDTO;
import com.devmatheusmarques.bibliotech_api.dto.AuthorResponseDTO;
import com.devmatheusmarques.bibliotech_api.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO response = authorService.authorRegister(authorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> editAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
        authorService.authorEdit(id, authorRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.authorDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        AuthorResponseDTO response = authorService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> response = authorService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}

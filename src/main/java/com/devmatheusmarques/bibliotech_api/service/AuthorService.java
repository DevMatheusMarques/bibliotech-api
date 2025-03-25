package com.devmatheusmarques.bibliotech_api.service;

import com.devmatheusmarques.bibliotech_api.dto.AuthorRequestDTO;
import com.devmatheusmarques.bibliotech_api.dto.AuthorResponseDTO;
import com.devmatheusmarques.bibliotech_api.model.Author;
import com.devmatheusmarques.bibliotech_api.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public AuthorResponseDTO authorRegister(AuthorRequestDTO authorRequestDTO) {
        try {
            Author author = modelMapper.map(authorRequestDTO, Author.class);

            author.setCreated_at(LocalDateTime.now());
            Author savedAuthor = authorRepository.save(author);
            return modelMapper.map(savedAuthor, AuthorResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void authorEdit(Long id, AuthorRequestDTO authorRequestDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado."));

        if (authorRequestDTO.getName() != null) {
            existingAuthor.setName(authorRequestDTO.getName());
        }
        if (authorRequestDTO.getBiography() != null) {
            existingAuthor.setBiography(authorRequestDTO.getBiography());
        }
        if (authorRequestDTO.getCountry() != null) {
            existingAuthor.setCountry(authorRequestDTO.getCountry());
        }
        if (authorRequestDTO.getBirthday_date() != null) {
            existingAuthor.setBirthday_date(authorRequestDTO.getBirthday_date());
        }

        existingAuthor.setUpdated_at(LocalDateTime.now());
        authorRepository.save(existingAuthor);
    }

    public void authorDelete(Long id) {
        try {
            Author existingAuthor = authorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

            authorRepository.delete(existingAuthor);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir autor: " + e.getMessage(), e);
        }
    }

    public AuthorResponseDTO findById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id inválido ou ausente.");
            }
            Author author = authorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

            return modelMapper.map(author, AuthorResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<AuthorResponseDTO> findAll() {
        try {
            List<Author> authors = authorRepository.findAll();

            return authors.stream()
                    .map(author -> modelMapper.map(author, AuthorResponseDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

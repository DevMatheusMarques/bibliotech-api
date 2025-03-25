package com.devmatheusmarques.bibliotech_api.service;

import com.devmatheusmarques.bibliotech_api.dto.AuthorEditDTO;
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

    public void authorEdit(Long id, AuthorEditDTO authorEditDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado."));

        if (authorEditDTO.getName() != null) {
            existingAuthor.setName(authorEditDTO.getName());
        }
        if (authorEditDTO.getBiography() != null) {
            existingAuthor.setBiography(authorEditDTO.getBiography());
        }
        if (authorEditDTO.getCountry() != null) {
            existingAuthor.setCountry(authorEditDTO.getCountry());
        }
        if (authorEditDTO.getBirthday_date() != null) {
            existingAuthor.setBirthday_date(authorEditDTO.getBirthday_date());
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

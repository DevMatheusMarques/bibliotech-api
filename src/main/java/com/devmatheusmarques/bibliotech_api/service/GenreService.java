package com.devmatheusmarques.bibliotech_api.service;

import com.devmatheusmarques.bibliotech_api.dto.GenreRequestDTO;
import com.devmatheusmarques.bibliotech_api.dto.GenreResponseDTO;
import com.devmatheusmarques.bibliotech_api.model.Genre;
import com.devmatheusmarques.bibliotech_api.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ModelMapper modelMapper;

    public GenreResponseDTO genreRegister(GenreRequestDTO genreRequestDTO) {
        try {
            Genre genre = modelMapper.map(genreRequestDTO, Genre.class);

            genre.setCreated_at(LocalDateTime.now());
            Genre savedGenre = genreRepository.save(genre);
            return modelMapper.map(savedGenre, GenreResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void genreEdit(Long id, GenreRequestDTO genreRequestDTO) {
        Genre existingGenre = genreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado."));

        if (genreRequestDTO.getName() != null) {
            existingGenre.setName(genreRequestDTO.getName());
        }
        if (genreRequestDTO.getDescription() != null) {
            existingGenre.setDescription(genreRequestDTO.getDescription());
        }

        existingGenre.setUpdated_at(LocalDateTime.now());
        genreRepository.save(existingGenre);
    }

    public void genreDelete(Long id) {
        try {
            Genre existingGenre = genreRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado"));

            genreRepository.delete(existingGenre);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir autor: " + e.getMessage(), e);
        }
    }

    public GenreResponseDTO findById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id inválido ou ausente.");
            }
            Genre genre = genreRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado"));

            return modelMapper.map(genre, GenreResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<GenreResponseDTO> findAll() {
        try {
            List<Genre> genres = genreRepository.findAll();

            return genres.stream()
                    .map(genre -> modelMapper.map(genre, GenreResponseDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

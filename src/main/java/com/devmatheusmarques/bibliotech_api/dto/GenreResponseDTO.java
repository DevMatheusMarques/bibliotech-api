package com.devmatheusmarques.bibliotech_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenreResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
}

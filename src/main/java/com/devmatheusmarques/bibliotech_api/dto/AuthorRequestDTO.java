package com.devmatheusmarques.bibliotech_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRequestDTO {
    private Long id;
    private String name;
    private String biography;
    private String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthday_date;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

package com.devmatheusmarques.bibliotech_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorEditDTO {
    private Long id;
    private String name;
    private String biography;
    private String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthday_date;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

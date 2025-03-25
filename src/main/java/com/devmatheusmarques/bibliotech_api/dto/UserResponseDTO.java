package com.devmatheusmarques.bibliotech_api.dto;

import com.devmatheusmarques.bibliotech_api.util.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
    private Status status;
    private LocalDateTime created_at;
}

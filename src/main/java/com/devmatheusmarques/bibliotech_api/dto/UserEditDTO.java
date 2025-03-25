package com.devmatheusmarques.bibliotech_api.dto;

import com.devmatheusmarques.bibliotech_api.util.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEditDTO {
    private String username;
    private String password;
    private String role;
    private Status status;
}

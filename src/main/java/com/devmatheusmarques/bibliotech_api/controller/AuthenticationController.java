package com.devmatheusmarques.bibliotech_api.controller;

import com.devmatheusmarques.bibliotech_api.dto.AuthenticationDTO;
import com.devmatheusmarques.bibliotech_api.dto.LoginResponseDTO;
import com.devmatheusmarques.bibliotech_api.dto.RegisterDTO;
import com.devmatheusmarques.bibliotech_api.model.User;
import com.devmatheusmarques.bibliotech_api.repository.UserRepository;
import com.devmatheusmarques.bibliotech_api.service.TokenService;
import com.devmatheusmarques.bibliotech_api.util.Status;
import com.devmatheusmarques.bibliotech_api.util.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        if(auth == null) return ResponseEntity.badRequest().build();

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(userRepository.findByLogin(data.login()).isPresent()) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserRole userRole = UserRole.fromString(data.role());
        Status status = Status.fromString(data.status());

        User newUser = new User(data.login(), encryptedPassword, userRole, status, LocalDateTime.now());

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}

package com.devmatheusmarques.bibliotech_api.model;

import com.devmatheusmarques.bibliotech_api.util.RoleConverter;
import com.devmatheusmarques.bibliotech_api.util.Status;
import com.devmatheusmarques.bibliotech_api.util.StatusConverter;
import com.devmatheusmarques.bibliotech_api.util.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Convert(converter = RoleConverter.class)
    @Column(name = "role", nullable = false)
    private UserRole role;
    @Convert(converter = StatusConverter.class)
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    public User(String login, String password, UserRole role, Status status , LocalDateTime created_at) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
        this.created_at = created_at;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

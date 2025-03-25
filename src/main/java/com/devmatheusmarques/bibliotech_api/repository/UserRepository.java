package com.devmatheusmarques.bibliotech_api.repository;

import com.devmatheusmarques.bibliotech_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}

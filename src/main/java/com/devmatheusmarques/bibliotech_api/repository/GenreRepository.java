package com.devmatheusmarques.bibliotech_api.repository;

import com.devmatheusmarques.bibliotech_api.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

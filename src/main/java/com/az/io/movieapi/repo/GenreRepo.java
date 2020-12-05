package com.az.io.movieapi.repo;

import com.az.io.movieapi.enums.GenreType;
import com.az.io.movieapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepo  extends JpaRepository<Genre,Integer> {

    Optional<Genre> findByName(String name);
}

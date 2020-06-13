package com.az.io.movieapi.service;

import com.az.io.movieapi.enums.GenreType;
import com.az.io.movieapi.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> getGenreByType(int genreType);

    Genre getGenreByName(String name);

    Genre getMovieGenreById(int id);
}

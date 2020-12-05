package com.az.io.movieapi.service;

import com.az.io.movieapi.enums.GenreType;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Tv;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GenreService {

    Genre getGenreByName(String name);

    Genre getMovieGenreById(int id);

    void addTvGenre(Set<Genre> genres, Tv tv);
}

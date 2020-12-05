package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Tv;
import com.az.io.movieapi.repo.GenreRepo;
import com.az.io.movieapi.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepo repo;


    @Override
    public Genre getGenreByName(String name) {
        return repo.findByName(name).orElseThrow(NotFoundException::new);
    }

    @Override
    public Genre getMovieGenreById(int id) {
        return repo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void addTvGenre(Set<Genre> genres, Tv tv) {
        genres.forEach(genre -> genre.setTvs(Collections.singletonList(tv)));
        repo.saveAll(genres);
    }
}

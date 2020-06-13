package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.enums.GenreType;
import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.repo.GenreRepo;
import com.az.io.movieapi.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepo repo;

    @Override
    public List<Genre> getGenreByType(int genreType) {
        return repo.findAllByType(genreType);
    }

    @Override
    public Genre getGenreByName(String name) {
        return repo.findByNameAndType(name,GenreType.MOVIE.getCode()).orElseThrow(NotFoundException::new);
    }

    @Override
    public Genre getMovieGenreById(int id) {
        return repo.findByIdAndType(id,GenreType.MOVIE.getCode()).orElseThrow(NotFoundException::new);
    }
}

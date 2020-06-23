package com.az.io.movieapi.service;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.model.Metadata;

import java.util.List;

public interface HomeService {

    List<Metadata<List<MovieDTO>>> getMovieHomePage();

    List<Metadata<List<TvDTO>>> getTvHomePage();

    void resetMovieCache();

    void resetTvCache();
}

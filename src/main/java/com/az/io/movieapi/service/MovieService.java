package com.az.io.movieapi.service;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.MovieDetails;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    Movie getMovieEntityById(String movieId);

    MovieDetails getMovieById(String movieId);

    List<MovieDTO> searchByTitle(String name);

    void addMovie(Movie movie);

    void addMovieList(List<Movie> movies);

    List<MovieDTO> getMoviesForHomepage(Pageable pageable);

    List<MovieDTO> getMoviesForHomepageByGenres(List<Genre> genres, Pageable pageable);

    Metadata getSimilarMovies(String movieId,Pageable pageable);

    List<MovieDTO> getMoviesByGenre(Genre genre,Pageable pageable);
}

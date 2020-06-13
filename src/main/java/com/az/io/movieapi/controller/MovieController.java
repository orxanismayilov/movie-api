package com.az.io.movieapi.controller;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.MovieDetails;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.model.ResponseObject;
import com.az.io.movieapi.service.GenreService;
import com.az.io.movieapi.service.MovieService;
import com.az.io.movieapi.util.LinkUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final GenreService genreService;

    @GetMapping
    public ResponseObject<Metadata> getMovies(Pageable pageable) {
        List<MovieDTO> movieDTOS = movieService.getMoviesForHomepage(pageable);
        return ResponseObject.getSuccessResponse(Metadata.builder()
                .movies(movieDTOS)
                .nextPage(LinkUtil.nextPageForMovies(pageable))
                .build());
    }

    @GetMapping("/{movieId}")
    public ResponseObject<MovieDetails> getMovieById(@PathVariable("movieId") String movieId) {
        return ResponseObject.getSuccessResponse(movieService.getMovieById(movieId));
    }


    @GetMapping("/genres")
    public ResponseObject<Metadata> getMoviesByGenre(@RequestParam List<String> genres,Pageable pageable) {
        List<Genre> genreList=genres.stream().map(genreService::getGenreByName).collect(Collectors.toList());
        return ResponseObject.getSuccessResponse(Metadata.builder()
                .nextPage(LinkUtil.nextPageMoviesByGenre(genres,pageable))
                .movies(movieService.getMoviesForHomepageByGenres(genreList,pageable))
                .build());
    }

    @GetMapping("/similar/{movieId}")
    public ResponseObject<?> getSimilarMovies(@PathVariable("movieId") String movieId,Pageable pageable) {
        return ResponseObject.getSuccessResponse(movieService.getSimilarMovies(movieId,pageable));
    }

    @GetMapping("/search")
    public ResponseObject<List<MovieDTO>> searchMovies(@RequestParam("q") String q) {
        return ResponseObject.getSuccessResponse(movieService.searchByTitle(q));
    }

    @PostMapping
    public void AddMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
    }

    @PostMapping("/list")
    public void addMovieList(@RequestBody List<Movie> movies) {
        movieService.addMovieList(movies);
    }
}
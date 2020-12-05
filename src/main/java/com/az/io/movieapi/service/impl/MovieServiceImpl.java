package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.controller.MovieController;
import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.MovieDetails;
import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.mapper.MovieDTOMapper;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.projections.MovieProjection;
import com.az.io.movieapi.repo.MovieRepo;
import com.az.io.movieapi.service.MovieService;
import com.az.io.movieapi.service.VideoService;
import com.az.io.movieapi.util.LinkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo repo;
    private final VideoService videoService;
    private static final Pageable pageablePopular = PageRequest.of(0, 30,
            Sort.by("popularity").descending());

    @Override
    public Movie getMovieEntityById(String movieId) {
        return repo.findById(movieId).orElseThrow(NotFoundException::new);
    }

    @Override
    public MovieDetails getMovieById(String movieId) {
        Movie movie = repo.findById(movieId).orElseThrow(NotFoundException::new);
        MovieDetails movieDetails = MovieDTOMapper.convertDetails(repo.findByImdbIdAndStatusIsTrue(movieId)
                .orElseThrow(NotFoundException::new));
        movieDetails.setMetadata(getSimilarMovies(movie.getImdbId(), PageRequest.of(0, 30)));
        return movieDetails;
    }

    @Override
    public Metadata<List<MovieDTO>> getSimilarMovies(String movieId, Pageable pageable) {
        List<MovieDTO> movieDTOS = MovieDTOMapper.convertValue(repo
                .findSimilarMoviesByMovieId(movieId, pageable));
        movieDTOS.forEach(movieHomePageDTO ->
                movieHomePageDTO.setLink(linkTo(methodOn(MovieController.class)
                        .getMovieById(movieHomePageDTO.getId()))
                        .toString()));
        return Metadata.<List<MovieDTO>>builder()
                .movies(movieDTOS)
                .title("Similar movies")
                .nextPage(LinkUtil.nextPageSimilarMovies(movieId, pageable))
                .responseType(0)
                .build();
    }

    @Override
    public Metadata<List<MovieDTO>> searchByTitle(String name, Pageable pageable) {
        List<MovieProjection> projections = repo.findDistinctByTitleContainingIgnoreCase(name,
                PageRequest.of(0, 31, Sort.by("popularity").descending()));
        List<MovieDTO> movieDTOS = MovieDTOMapper.convertProjection(projections);
        movieDTOS.forEach(movieDTO -> movieDTO.setLink(linkTo(methodOn(MovieController.class)
                .getMovieById(movieDTO.getId())).toString()));
        return Metadata.<List<MovieDTO>>builder()
                .nextPage(LinkUtil.nextPageSearchMovies(name, pageable))
                .movies(movieDTOS)
                .build();
    }

    @Override
    @Transactional
    public void addMovie(Movie movie) {
        movie.getVideos().removeIf(video -> videoService.isVideoExists(movie, video.getLanguage()));
        movie.getVideos().forEach(video -> video.setMovie(movie));
        repo.save(movie);
    }

    @Override
    public List<MovieDTO> getMoviesForHomepage(Pageable pageable) {
        List<MovieDTO> movieDTOS = repo.findAll(pageable)
                .stream()
                .map(MovieDTOMapper::convertValue)
                .collect(Collectors.toList());
        movieDTOS.forEach(movieHomePageDTO ->
                movieHomePageDTO
                        .setLink(linkTo(methodOn(MovieController.class)
                                .getMovieById(movieHomePageDTO.getId())).toString()));
        return movieDTOS;
    }

    @Override
    @Transactional
    public void addMovieList(List<Movie> movies) {
        movies.forEach(movie -> movie.getVideos().removeIf(video -> videoService.isVideoExists(movie, video.getLanguage())));
        movies.forEach(movie -> movie.getVideos().forEach(video -> video.setMovie(new Movie(movie.getImdbId()))));
        repo.saveAll(movies);
    }

    @Override
    public List<MovieDTO> getMoviesForHomepageByGenres(List<Genre> genres, Pageable pageable) {
        List<MovieDTO> movieDTOS = MovieDTOMapper.convertProjection(repo.findDistinctByGenresIn(genres, pageable));
        movieDTOS.forEach(movieHomePageDTO ->
                movieHomePageDTO.setLink(linkTo(methodOn(MovieController.class)
                        .getMovieById(movieHomePageDTO.getId()))
                        .toString()));
        return movieDTOS;
    }

    @Override
    public List<MovieDTO> getMoviesByGenre(Genre genre, Pageable pageable) {
        List<MovieDTO> movieDTOS = MovieDTOMapper.convertProjection(repo.findDistinctByGenres_Id(genre.getId(), pageable));
        movieDTOS.forEach(movieHomePageDTO ->
                movieHomePageDTO.setLink(linkTo(methodOn(MovieController.class)
                        .getMovieById(movieHomePageDTO.getId()))
                        .toString()));
        return movieDTOS;
    }

    @Override
    public List<MovieDTO> getMoviesByLanguage(String language, Pageable pageable) {
        List<MovieDTO> movieDTOS = MovieDTOMapper.convertProjection(repo.findDistinctByVideos_Language(language, pageable));
        movieDTOS.forEach(movieHomePageDTO ->
                movieHomePageDTO.setLink(linkTo(methodOn(MovieController.class)
                        .getMovieById(movieHomePageDTO.getId()))
                        .toString()));
        return movieDTOS;
    }
}

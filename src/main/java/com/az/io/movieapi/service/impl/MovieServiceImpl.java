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
        Movie movie=repo.findById(movieId).orElseThrow(NotFoundException::new);
        MovieDetails movieDetails= MovieDTOMapper.convertDetails(repo.findByImdbIdAndStatusIsTrue(movieId)
                .orElseThrow(NotFoundException::new));
        movieDetails.setMetadata(getSimilarMovies(movie.getImdbId(),pageablePopular));
        return movieDetails;
    }

    public Metadata getSimilarMovies(String movieId,Pageable pageable) {
        Movie movie=repo.findById(movieId).orElseThrow(NotFoundException::new);
        List<MovieDTO> movieDTOS = MovieDTOMapper.convertProjection(repo.findDistinctByKeywordsIn(movie.getKeywords(), pageable));
        movieDTOS.removeIf(movieDTO -> movieDTO.getId().equals(movie.getImdbId()));
        movieDTOS.forEach(movieHomePageDTO ->
                movieHomePageDTO.setLink(linkTo(methodOn(MovieController.class)
                        .getMovieById(movieHomePageDTO.getId()))
                        .toString()));
        return Metadata.builder()
                .movies(movieDTOS)
                .title("Similar movies")
                .nextPage(LinkUtil.nextPageSimilarMovies(movie.getImdbId(), pageablePopular))
                .build();
    }

    @Override
    public List<MovieDTO> searchByTitle(String name) {
        List<MovieProjection> projections= repo.findByTitleContainingIgnoreCase(name,
                        PageRequest.of(0,5,Sort.by("popularity").descending()));
        List<MovieDTO> movieDTOS=MovieDTOMapper.convertProjection(projections);
        movieDTOS.forEach(movieDTO -> movieDTO.setLink(linkTo(methodOn(MovieController.class)
                .getMovieById(movieDTO.getId())).toString()));
        return movieDTOS;
    }

    @Override
    public void addMovie(Movie movie) {
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
    public void addMovieList(List<Movie> movies) {
        movies.forEach(movie -> movie.getVideos().removeIf(video -> videoService.isVideoExists(movie,video.getLanguage())));
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
    public List<MovieDTO> getMoviesByGenre(Genre genre,Pageable pageable) {
        List<MovieDTO> movieDTOS = MovieDTOMapper.convertProjection(repo.findDistinctByGenres_Id(genre.getId(),pageable));
        movieDTOS.forEach(movieHomePageDTO ->
                movieHomePageDTO.setLink(linkTo(methodOn(MovieController.class)
                        .getMovieById(movieHomePageDTO.getId()))
                        .toString()));
        return movieDTOS;
    }
}

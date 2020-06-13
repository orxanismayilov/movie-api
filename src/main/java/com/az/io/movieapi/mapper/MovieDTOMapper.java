package com.az.io.movieapi.mapper;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.MovieDetails;
import com.az.io.movieapi.model.*;
import com.az.io.movieapi.projections.MovieProjection;
import com.az.io.movieapi.projections.VideoProjection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieDTOMapper {

    public static MovieDTO convertValue(Movie movie) {
        MovieDTO homePageDTO=new MovieDTO();
        homePageDTO.setId(movie.getImdbId());
        homePageDTO.setTitle(movie.getTitle());
        homePageDTO.setImdbRating(movie.getImdbRating());
        homePageDTO.setPosterPath(movie.getPosterPath());
        homePageDTO.setLanguages(movie.getVideos().stream().map(Video::getLanguage).collect(Collectors.toList()));
        return homePageDTO;
    }

    public static List<MovieDTO> convertValue(List<Movie> movies) {
        return movies.stream().map(MovieDTOMapper::convertValue).collect(Collectors.toList());
    }

    public static MovieDetails convertDetails(Movie movie) {
        MovieDetails details=new MovieDetails();
        details.setImdbId(movie.getImdbId());
        details.setTitle(movie.getTitle());
        details.setBackdropPath(movie.getBackdropPath());
        details.setImdbRating(movie.getImdbRating());
        details.setPopularity(movie.getPopularity());
        details.setOverview(movie.getOverview());
        details.setReleaseDate(movie.getReleaseDate());
        details.setRuntime(movie.getRuntime());
        details.setPosterPath(movie.getPosterPath());
        details.setTrailerPath(movie.getTrailerPath());
        details.setGenres(movie.getGenres().stream().map(Genre::getName).collect(Collectors.toList()));
        details.setCompanies(movie.getCompanies().stream().map(Company::getName).collect(Collectors.toList()));
        details.setCast(movie.getCast().stream().limit(5).map(Cast::getName).collect(Collectors.toList()));
        details.setCountries(movie.getCountries().stream().map(Country::getName).collect(Collectors.toList()));
        details.setDirector(movie.getCrew().stream()
                .filter(crew -> crew.getJob().equals("Director"))
                .map(Crew::getName).collect(Collectors.toList()));
        details.setWriter(movie.getCrew().stream()
                .filter(crew -> crew.getJob().equals("Writer"))
                .map(Crew::getName).collect(Collectors.toList()));
        details.setVideos(VideoMapper.videoLinkMapper(movie.getVideos()));
        details.setComments(CommentMapper.convertValue(movie.getComments()));
        return details;
    }

    public static List<MovieDTO> convertProjection(List<MovieProjection> projections) {
        return projections.stream().map(MovieDTOMapper::convertProjection).collect(Collectors.toList());
    }

    public static MovieDTO convertProjection(MovieProjection projection) {
        MovieDTO movieDTO=new MovieDTO();
        movieDTO.setId(projection.getImdbId());
        movieDTO.setImdbRating(projection.getImdbRating());
        movieDTO.setPosterPath(projection.getPosterPath());
        movieDTO.setTitle(projection.getTitle());
        movieDTO.setLanguages(projection.getVideos()
                .stream()
                .map(VideoProjection::getLanguage)
                .collect(Collectors.toList()));
        return movieDTO;
    }
}

package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.cache.CustomCacheManager;
import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.service.HomeService;
import com.az.io.movieapi.service.MovieService;
import com.az.io.movieapi.util.LinkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private static final Pageable pageablePopular = PageRequest.of(0, 30,
            Sort.by("popularity").descending());
    private static final Pageable pageableLatest = PageRequest.of(0, 30,
            Sort.by("releaseDate").descending());
    private final MovieService movieService;
    private final CustomCacheManager cacheManager;

    @Override
    public List<Metadata<List<MovieDTO>>> getHomePage() {
        if (!cacheManager.isExist("home") || cacheManager.isExpired()) {
            resetCache();
        }
        return (List<Metadata<List<MovieDTO>>>) cacheManager.get("home");
    }

    public  void resetCache() {
        List<Metadata<List<MovieDTO>>> metadata = new ArrayList<>();
        metadata.add(getTrendingMovies());
        metadata.add(getLatestMovies());
        metadata.add(getActionAndAdventureMovies());
        metadata.add(getMoviesByGenre(new Genre(35,"Comedy")));
        metadata.add(getSciFiAndActionMovies());
        metadata.add(getMoviesByGenre(new Genre(53,"Thriller")));
        metadata.add(getMoviesByGenre(new Genre(878,"Science Fiction")));
        metadata.add(getMoviesByGenre(new Genre(27,"Horror")));
        metadata.add(getMoviesByGenre(new Genre(10749,"Romance")));
        metadata.add(getMoviesByGenre(new Genre(10751,"Family")));
        cacheManager.add("home",metadata);
    }

    private Metadata<List<MovieDTO>> getSciFiAndActionMovies() {
        List<Genre> genres=new ArrayList<>();
        genres.add(new Genre(28,"Action"));
        genres.add(new Genre(878,"Science Fiction"));
        return getMoviesByMultipleGenres(genres,"Science fiction action movies");
    }

    private Metadata<List<MovieDTO>> getActionAndAdventureMovies() {
        List<Genre> genres=new ArrayList<>();
        genres.add(new Genre(28,"Action"));
        genres.add(new Genre(12,"Adventure"));
        return getMoviesByMultipleGenres(genres,"Action adventure movies");
    }

    private Metadata<List<MovieDTO>> getTrendingMovies() {
        Metadata<List<MovieDTO>> popularMovies = new Metadata<>();
        popularMovies.setTitle("Trending movies");
        popularMovies.setNextPage(LinkUtil.nextPageForMovies(pageablePopular));
        popularMovies.setData(movieService.getMoviesForHomepage(pageablePopular));
        return popularMovies;
    }

    private Metadata<List<MovieDTO>> getLatestMovies() {
        Metadata<List<MovieDTO>> latestMovies = new Metadata<>();
        latestMovies.setTitle("Latest movies");
        latestMovies.setData(movieService.getMoviesForHomepage(pageableLatest));
        latestMovies.setNextPage(LinkUtil.nextPageForMovies(pageableLatest));
        return latestMovies;
    }

    private Metadata<List<MovieDTO>> getMoviesByGenre(Genre genre) {
        Metadata<List<MovieDTO>> movies = new Metadata<>();
        Pageable pageable=getGenrePageAble();
        movies.setTitle(genre.getName()+" movies");
        movies.setData(movieService.getMoviesByGenre(genre, pageable));
        movies.setNextPage(LinkUtil.nextPageMoviesByGenre(
                getGenreNames(Collections.singletonList(genre)),pageable));
        return movies;
    }

    private Metadata<List<MovieDTO>> getMoviesByMultipleGenres(List<Genre> genres,String title) {
        Metadata<List<MovieDTO>> movies = new Metadata<>();
        movies.setTitle(title);
        Pageable pageable=getGenrePageAble();
        movies.setData(movieService.getMoviesForHomepageByGenres(genres, pageable));
        movies.setNextPage(LinkUtil.nextPageMoviesByGenre(
                getGenreNames(genres),pageable));
        return movies;
    }

    private List<String> getGenreNames(List<Genre> genres) {
        return genres.stream().map(Genre::getName).collect(Collectors.toList());
    }

    private Pageable getGenrePageAble(){
        return PageRequest.of(ThreadLocalRandom.current().nextInt(0, 5 + 1), 30,
                Sort.by("popularity").descending());
    }
}

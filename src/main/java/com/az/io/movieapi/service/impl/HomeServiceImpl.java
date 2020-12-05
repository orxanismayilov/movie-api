package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.cache.CustomCacheManager;
import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.service.HomeService;
import com.az.io.movieapi.service.MovieService;
import com.az.io.movieapi.service.TvService;
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
    private static  final String MOVIE_CACHE_KEY="movieHome";
    private static final String TV_CACHE_KEY="tvHome";
    private final MovieService movieService;
    private final CustomCacheManager cacheManager;
    private final TvService tvService;

    @Override
    public List<Metadata<List<MovieDTO>>> getMovieHomePage() {
        if (!cacheManager.isMovieExist(MOVIE_CACHE_KEY) || cacheManager.isExpired(MOVIE_CACHE_KEY)) {
            resetMovieCache();
        }
        return cacheManager.getMovieCache(MOVIE_CACHE_KEY);
    }

    @Override
    public List<Metadata<List<TvDTO>>> getTvHomePage() {
        if (!cacheManager.isMovieExist(TV_CACHE_KEY) || cacheManager.isExpired(TV_CACHE_KEY)) {
            resetTvCache();
        }
        return cacheManager.getTvCache(TV_CACHE_KEY);
    }

    @Override
    public void resetTvCache() {
        List<Metadata<List<TvDTO>>> metadata = new ArrayList<>();
        metadata.add(getTrendingTvs());
        metadata.add(getLatestTvs());
        metadata.add(getActionAndAdventureTvs());
        metadata.add(getTvsByGenre(new Genre(35,"Comedy")));
        metadata.add(getSciFiAndActionTvs());
        metadata.add(getTvsByGenre(new Genre(53,"Thriller")));
        metadata.add(getTvsByGenre(new Genre(878,"Science Fiction")));
        metadata.add(getTvsByGenre(new Genre(27,"Horror")));
        metadata.add(getTvsByGenre(new Genre(10749,"Romance")));
        metadata.add(getTvsByGenre(new Genre(10751,"Family")));
        cacheManager.addSeriesCache(TV_CACHE_KEY,metadata);
    }

    public  void resetMovieCache() {
        List<Metadata<List<MovieDTO>>> metadata = new ArrayList<>();
        metadata.add(getTrendingMovies());
        metadata.add(getLatestMovies());
        metadata.add(getMoviesBuLang("tr"));
        metadata.add(getMoviesBuLang("rus"));
        metadata.add(getActionAndAdventureMovies());
        metadata.add(getMoviesByGenre(new Genre(35,"Comedy")));
        metadata.add(getSciFiAndActionMovies());
        metadata.add(getMoviesByGenre(new Genre(53,"Thriller")));
        metadata.add(getMoviesByGenre(new Genre(878,"Science Fiction")));
        metadata.add(getMoviesByGenre(new Genre(27,"Horror")));
        metadata.add(getMoviesByGenre(new Genre(10749,"Romance")));
        metadata.add(getMoviesByGenre(new Genre(10751,"Family")));
        metadata.add(getMoviesByGenre(new Genre(18,"Drama")));
        cacheManager.addMovieCache(MOVIE_CACHE_KEY,metadata);
    }

    private Metadata<List<MovieDTO>> getMoviesBuLang(String lang) {
        Metadata<List<MovieDTO>> metadata=new Metadata<>();
        metadata.setResponseType(0);
        metadata.setTitle(lang.equals("tr")?"Movies in Turkish":"Movies in Russian");
        metadata.setMovies(movieService.getMoviesByLanguage(lang,pageablePopular));
        metadata.setNextPage(LinkUtil.nextPageMoviesByLang(lang,pageablePopular));
        return metadata;
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
        popularMovies.setResponseType(0);
        popularMovies.setTitle("Trending movies");
        popularMovies.setNextPage(LinkUtil.nextPageForMovies(pageablePopular));
        popularMovies.setMovies(movieService.getMoviesForHomepage(pageablePopular));
        return popularMovies;
    }

    private Metadata<List<MovieDTO>> getLatestMovies() {
        Metadata<List<MovieDTO>> latestMovies = new Metadata<>();
        latestMovies.setResponseType(0);
        latestMovies.setTitle("Latest movies");
        latestMovies.setMovies(movieService.getMoviesForHomepage(pageableLatest));
        latestMovies.setNextPage(LinkUtil.nextPageForMovies(pageableLatest));
        return latestMovies;
    }

    private Metadata<List<TvDTO>> getTrendingTvs() {
        Metadata<List<TvDTO>> popularTvs = new Metadata<>();
        popularTvs.setResponseType(1);
        popularTvs.setTitle("Trending series");
        popularTvs.setNextPage(LinkUtil.nextPageForTvs(pageablePopular));
        popularTvs.setMovies(tvService.getTvsForHomepage(pageablePopular));
        return popularTvs;
    }

    private Metadata<List<TvDTO>> getLatestTvs() {
        Metadata<List<TvDTO>> latestTvs = new Metadata<>();
        latestTvs.setResponseType(1);
        latestTvs.setTitle("Latest series");
        latestTvs.setMovies(tvService.getTvsForHomepage(pageableLatest));
        latestTvs.setNextPage(LinkUtil.nextPageForTvs(pageableLatest));
        return latestTvs;
    }

    private Metadata<List<TvDTO>> getActionAndAdventureTvs() {
        List<Genre> genres=new ArrayList<>();
        genres.add(new Genre(28,"Action"));
        genres.add(new Genre(12,"Adventure"));
        return getTvsByMultipleGenres(genres,"Action adventure series");
    }

    private Metadata<List<TvDTO>> getSciFiAndActionTvs() {
        List<Genre> genres=new ArrayList<>();
        genres.add(new Genre(28,"Action"));
        genres.add(new Genre(878,"Science Fiction"));
        return getTvsByMultipleGenres(genres,"Science fiction action series");
    }

    private Metadata<List<TvDTO>> getTvsByGenre(Genre genre) {
        Metadata<List<TvDTO>> tvs = new Metadata<>();
        tvs.setResponseType(1);
        Pageable pageable=getGenrePageAble();
        tvs.setTitle(genre.getName()+" series");
        tvs.setMovies(tvService.getTvByGenre(genre, pageable));
        tvs.setNextPage(LinkUtil.nextPageTvsByGenre(
                getGenreNames(Collections.singletonList(genre)),pageable));
        return tvs;
    }

    private Metadata<List<TvDTO>> getTvsByMultipleGenres(List<Genre> genres,String title) {
        Metadata<List<TvDTO>> tvs = new Metadata<>();
        tvs.setResponseType(1);
        tvs.setTitle(title);
        Pageable pageable=getGenrePageAble();
        tvs.setMovies(tvService.getTvsForHomepageByGenres(genres, pageable));
        tvs.setNextPage(LinkUtil.nextPageTvsByGenre(
                getGenreNames(genres),pageable));
        return tvs;
    }

    private Metadata<List<MovieDTO>> getMoviesByGenre(Genre genre) {
        Metadata<List<MovieDTO>> movies = new Metadata<>();
        movies.setResponseType(0);
        Pageable pageable=getGenrePageAble();
        movies.setTitle(genre.getName()+" movies");
        movies.setMovies(movieService.getMoviesByGenre(genre, pageable));
        movies.setNextPage(LinkUtil.nextPageMoviesByGenre(
                getGenreNames(Collections.singletonList(genre)),pageable));
        return movies;
    }

    private Metadata<List<MovieDTO>> getMoviesByMultipleGenres(List<Genre> genres,String title) {
        Metadata<List<MovieDTO>> movies = new Metadata<>();
        movies.setResponseType(0);
        movies.setTitle(title);
        Pageable pageable=getGenrePageAble();
        movies.setMovies(movieService.getMoviesForHomepageByGenres(genres, pageable));
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

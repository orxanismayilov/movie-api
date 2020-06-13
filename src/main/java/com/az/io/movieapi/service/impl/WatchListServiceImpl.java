package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.mapper.MovieDTOMapper;
import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.model.MovieWatchlist;
import com.az.io.movieapi.model.Watchlist;
import com.az.io.movieapi.repo.WatchlistRepo;
import com.az.io.movieapi.service.MovieService;
import com.az.io.movieapi.service.MovieWatchlistService;
import com.az.io.movieapi.service.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchListServiceImpl implements WatchListService {

    private final WatchlistRepo watchlistRepo;
    private final MovieService movieService;
    private final MovieWatchlistService movieWatchlistService;

    @Override
    public void saveWatchList(Watchlist watchList) {
        watchlistRepo.save(watchList);
    }

    @Override
    public List<MovieDTO> getUserWatchList(String userId) {
        List<MovieWatchlist> movieWatchlists=new ArrayList<>(watchlistRepo
                .findByUser_Id(userId)
                .orElseThrow(NotFoundException::new)
                .getMovieWatchlist());
        return MovieDTOMapper.convertValue(movieWatchlists.stream().map(MovieWatchlist::getMovie).collect(Collectors.toList()));
    }

    @Override
    public void deleteMovieFromWatchList(String userId,String movieId) {
        movieWatchlistService.deleteMovieWatchlistByMovieId(userId,movieId);
    }

    @Override
    public void addMovieWatchList(String userId, String movieId) {
        MovieWatchlist movieWatchlist=new MovieWatchlist();
        Movie movie=movieService.getMovieEntityById(movieId);
        Watchlist watchList=watchlistRepo.findByUser_Id(userId).orElseThrow(NotFoundException::new);
        movieWatchlist.setMovie(movie);
        movieWatchlist.setWatchlist(watchList);
        movieWatchlistService.addMovieWatchlist(movieWatchlist);
    }
}

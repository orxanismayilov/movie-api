package com.az.io.movieapi.service;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.model.Watchlist;

import java.util.List;

public interface WatchListService {

    void saveWatchList(Watchlist watchList);

    List<MovieDTO> getUserWatchList(String userId);

    void deleteMovieFromWatchList(String userId,String movieId);

    void addMovieWatchList(String userId, String movieId);
}

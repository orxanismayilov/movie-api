package com.az.io.movieapi.service;

import com.az.io.movieapi.model.MovieWatchlist;

public interface MovieWatchlistService {

    public void addMovieWatchlist(MovieWatchlist movieWatchlist);

    void deleteMovieWatchlistByMovieId(String userId,String movieId);
}

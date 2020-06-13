package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.MovieWatchlist;
import com.az.io.movieapi.repo.MovieWatchListRepo;
import com.az.io.movieapi.service.MovieWatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MovieWatchlistServiceImpl implements MovieWatchlistService {

    private final MovieWatchListRepo repo;

    @Override
    public void addMovieWatchlist(MovieWatchlist movieWatchlist) {
        repo.save(movieWatchlist);
    }

    @Override
    @Transactional
    public void deleteMovieWatchlistByMovieId(String userId,String movieId) {
        repo.deleteByMovie_ImdbIdAndWatchlist_User_Id(movieId,userId);
    }
}

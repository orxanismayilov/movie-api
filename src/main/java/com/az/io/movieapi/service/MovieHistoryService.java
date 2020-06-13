package com.az.io.movieapi.service;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.model.MovieHistory;

import java.util.List;

public interface MovieHistoryService {

    void deleteByMovieIdAndUserId(String movieId,String userId);

    void addMovieHistory(MovieHistory movieHistory);
}

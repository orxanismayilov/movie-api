package com.az.io.movieapi.service;

import com.az.io.movieapi.dto.MovieDTO;

import java.util.List;

public interface HistoryService {

    List<MovieDTO> getUserHistory(String userId);

    void removeMovieHistory(String userId,String movieId);

    void addMovieHistory(String userId,String movieId);
}

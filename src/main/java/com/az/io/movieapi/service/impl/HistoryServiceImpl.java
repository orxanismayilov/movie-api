package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.mapper.MovieDTOMapper;
import com.az.io.movieapi.model.*;
import com.az.io.movieapi.repo.HistoryRepo;
import com.az.io.movieapi.service.HistoryService;
import com.az.io.movieapi.service.MovieHistoryService;
import com.az.io.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final MovieHistoryService movieHistoryService;
    private final HistoryRepo repo;
    private final MovieService movieService;

    @Override
    public List<MovieDTO> getUserHistory(String userId) {
        List<MovieHistory> movieHistories= new ArrayList<>(repo.findByUser_Id(userId)
                .orElseThrow(NotFoundException::new)
                .getMovieHistories());
        List<Movie> movies=movieHistories
                .stream()
                .map(MovieHistory::getMovie)
                .collect(Collectors.toList());
        return MovieDTOMapper.convertValue(movies);
    }

    @Override
    public void removeMovieHistory(String userId, String movieId) {
        movieHistoryService.deleteByMovieIdAndUserId(movieId,userId);
    }

    @Override
    public void addMovieHistory(String userId, String movieId) {
        MovieHistory movieHistory=new MovieHistory();
        Movie movie=movieService.getMovieEntityById(movieId);
        History history=repo.findByUser_Id(userId).orElseThrow(NotFoundException::new);
        movieHistory.setMovie(movie);
        movieHistory.setHistory(history);
        movieHistoryService.addMovieHistory(movieHistory);
    }
}

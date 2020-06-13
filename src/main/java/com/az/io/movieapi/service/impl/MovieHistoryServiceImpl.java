package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.MovieHistory;
import com.az.io.movieapi.repo.MovieHistoryRepo;
import com.az.io.movieapi.service.MovieHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MovieHistoryServiceImpl implements MovieHistoryService {

    private final MovieHistoryRepo repo;

    @Override
    @Transactional
    public void deleteByMovieIdAndUserId(String movieId, String userId) {
        repo.deleteByMovie_ImdbIdAndHistory_User_Id(movieId,userId);
    }

    @Override
    public void addMovieHistory(MovieHistory movieHistory) {
        repo.save(movieHistory);
    }
}

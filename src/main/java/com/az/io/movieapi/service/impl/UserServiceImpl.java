package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.model.User;
import com.az.io.movieapi.model.History;
import com.az.io.movieapi.model.Watchlist;
import com.az.io.movieapi.repo.HistoryRepo;
import com.az.io.movieapi.repo.UserRepo;
import com.az.io.movieapi.repo.WatchlistRepo;
import com.az.io.movieapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final WatchlistRepo watchlistRepo;
    private final HistoryRepo historyRepo;

    @Override
    public void addUser(User user) {
        user.setHistory(new History());
        user.setWatchlist(new Watchlist());
        userRepo.save(user);
    }

    @Override
    public User getUser(String userId) {
        return userRepo.findById(userId).orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }
}

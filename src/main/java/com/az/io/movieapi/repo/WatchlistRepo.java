package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchlistRepo extends JpaRepository<Watchlist,Integer> {

    Optional<Watchlist> findByUser_Id(String userId);
}

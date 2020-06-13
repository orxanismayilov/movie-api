package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.MovieWatchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieWatchListRepo extends JpaRepository<MovieWatchlist,String> {

    void deleteByMovie_ImdbIdAndWatchlist_User_Id(String imdbId,String userId);

    List<MovieWatchlist> findAllByWatchlist_Id(int watchlistId);
}

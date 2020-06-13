package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.MovieHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieHistoryRepo extends JpaRepository<MovieHistory,Integer> {

    void deleteByMovie_ImdbIdAndHistory_User_Id(String movieId,String userId);
}

package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepo extends JpaRepository<Season,Integer> {
}

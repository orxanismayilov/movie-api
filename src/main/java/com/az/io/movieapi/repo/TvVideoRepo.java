package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.TvVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvVideoRepo extends JpaRepository<TvVideo,String> {
}

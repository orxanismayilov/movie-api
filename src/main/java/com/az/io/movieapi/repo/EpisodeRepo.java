package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepo extends JpaRepository<Episode,Long> {
}

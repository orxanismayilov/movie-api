package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Subtitle;
import com.az.io.movieapi.projections.SubtitleProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtitleRepo extends JpaRepository<Subtitle,Integer> {

    List<SubtitleProjection> findAllByMovieId(String movieId);
}

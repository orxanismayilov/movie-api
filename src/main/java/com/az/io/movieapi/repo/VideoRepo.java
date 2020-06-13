package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepo  extends JpaRepository<Video,String> {

    Optional<Video> findByMovieAndLanguage(Movie movie, String language);
}

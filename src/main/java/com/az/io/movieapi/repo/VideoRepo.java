package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepo  extends JpaRepository<Video,String> {

    List<Video> findAllByMovieAndLanguage(Movie movie, String language);

    Optional<Video> findByMovie_ImdbIdAndLanguage(String imdbId, String language);

    void deleteAllByMovie_ImdbId(String imdbId);

}

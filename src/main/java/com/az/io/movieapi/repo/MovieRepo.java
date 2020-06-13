package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Keyword;
import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.projections.MovieProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie, String> {


    Optional<Movie> findByImdbIdAndStatusIsTrue(String id);

    List<MovieProjection> findDistinctByGenresIn(Collection<Genre> genres, Pageable pageable);

    List<MovieProjection> findDistinctByGenres_Id(Integer genres_id,Pageable pageable);

    List<MovieProjection> findDistinctByKeywordsIn(Collection<Keyword> keywords, Pageable pageable);

    List<MovieProjection> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}

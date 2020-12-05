package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Keyword;
import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.projections.MovieProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie, String> {


    Optional<Movie> findByImdbIdAndStatusIsTrue(String id);

    List<MovieProjection> findDistinctByGenresIn(Collection<Genre> genres, Pageable pageable);

    List<MovieProjection> findDistinctByGenres_Id(Integer genres_id,Pageable pageable);

    @Query(value = "SELECT m.*\n" +
            "FROM movie m\n" +
            "JOIN (\n" +
            "        SELECT m.imdb_id, COUNT(*) AS cat_frequency\n" +
            "        FROM movie m\n" +
            "        JOIN movie_keyword mc ON m.imdb_id = mc.movie_id\n" +
            "        WHERE mc.keyword_id IN ( SELECT c.keyword_id\n" +
            "                                  FROM movie_keyword c\n" +
            "                                  WHERE c.movie_id = :imdbId )\n" +
            "        GROUP BY m.imdb_id\n" +
            "     ) f\n" +
            "  ON m.imdb_id = f.imdb_id\n" +
            "WHERE m.imdb_id <> :imdbId\n" +
            "ORDER BY f.cat_frequency DESC",nativeQuery = true)
    List<Movie> findSimilarMoviesByMovieId(String imdbId, Pageable pageable);

    List<MovieProjection> findDistinctByTitleContainingIgnoreCase(String title, Pageable pageable);

    List<MovieProjection> findDistinctByVideos_Language(String language,Pageable pageable);
}

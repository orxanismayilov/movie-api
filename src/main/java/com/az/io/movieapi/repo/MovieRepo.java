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
            "            FROM movie m,movie_keyword mk, keyword k\n" +
            "            WHERE mk.keyword_id = k.id\n" +
            "            AND (k.name IN :keywords)\n" +
            "            AND m.imdb_id = mk.movie_id\n" +
            "            GROUP BY m.imdb_id",nativeQuery = true)
    List<Movie> findDistinctByKeywordsIn(@Param("keywords") Collection<String> keywords, Pageable pageable);

    List<MovieProjection> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    List<MovieProjection> findDistinctByVideos_Language(String language,Pageable pageable);
}

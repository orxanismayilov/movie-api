package com.az.io.movieapi.repo;

import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Keyword;
import com.az.io.movieapi.model.Tv;
import com.az.io.movieapi.projections.TvProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TvRepo extends JpaRepository<Tv,Long> {

    Optional<Tv> findByImdbId(String imdbId);

    @Query("select new com.az.io.movieapi.dto.TvDTO(t.imdbId,t.name,t.posterPath,t.imdbRating) " +
            "from Tv t where t.genres in :genres")
    List<TvDTO> findDistinctByGenresIn(Collection<Genre> genres, Pageable pageable);

    List<TvProjection> findDistinctByGenres_Id(Integer genres_id, Pageable pageable);

    @Query(value = "SELECT m.*\n" +
            "FROM tv m\n" +
            "JOIN (\n" +
            "        SELECT m.imdb_id, COUNT(*) AS cat_frequency\n" +
            "        FROM tv m\n" +
            "        JOIN tv_keyword mc ON m.imdb_id = mc.tv_id\n" +
            "        WHERE mc.keyword_id IN ( SELECT c.keyword_id\n" +
            "                                  FROM tv_keyword c\n" +
            "                                  WHERE c.tv_id = :imdbId )\n" +
            "        GROUP BY m.imdb_id\n" +
            "     ) f\n" +
            "  ON m.imdb_id = f.imdb_id\n" +
            "WHERE m.imdb_id <> :imdbId\n" +
            "ORDER BY f.cat_frequency DESC",nativeQuery = true)
    List<TvProjection> findDistinctByKeywordsIn(String imdbId, Pageable pageable);

    @Query("select new com.az.io.movieapi.dto.TvDTO(t.imdbId,t.name,t.posterPath,t.imdbRating) " +
            "from Tv t")
    List<TvDTO> findAllTvDto(Pageable pageable);
}

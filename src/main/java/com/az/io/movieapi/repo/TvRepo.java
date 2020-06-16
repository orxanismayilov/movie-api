package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Keyword;
import com.az.io.movieapi.model.Tv;
import com.az.io.movieapi.projections.TvProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TvRepo extends JpaRepository<Tv,String> {

    Optional<Tv> findByImdbIdAndStatusIsTrue(String id);

    List<TvProjection> findDistinctByGenresIn(Collection<Genre> genres, Pageable pageable);

    List<TvProjection> findDistinctByGenres_Id(Integer genres_id, Pageable pageable);

    List<TvProjection> findDistinctByKeywordsIn(Collection<Keyword> keywords, Pageable pageable);

    List<TvProjection> findByNameContainingIgnoreCase(String title, Pageable pageable);
}

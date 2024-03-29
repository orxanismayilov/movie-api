package com.az.io.movieapi.service;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.dto.TvDetails;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.Tv;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TvService {

    Tv getTvEntityById(String tvId);

    TvDetails getTvDetailsByImdbId(String tvId);

    void addTv(Tv tv);

    void addTvList(List<Tv> tvs);

    List<TvDTO> getTvsForHomepage(Pageable pageable);

    List<TvDTO> getTvsForHomepageByGenres(List<Genre> genres, Pageable pageable);

    Metadata<List<TvDTO>> getSimilarTvs(String tvId, Pageable pageable);

    List<TvDTO> getTvByGenre(Genre genre,Pageable pageable);
}

package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.dto.TvDetails;
import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.Tv;
import com.az.io.movieapi.repo.TvRepo;
import com.az.io.movieapi.service.TvService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TvServiceImpl implements TvService {

    private final TvRepo repo;

    @Override
    public Tv getTvEntityById(String tvId) {
        return repo.findById(tvId).orElseThrow(NotFoundException::new);
    }

    @Override
    public TvDetails getTvDetailsById(String tvId) {
        return null;
    }

    @Override
    public List<MovieDTO> searchByTitle(String name) {
        return null;
    }

    @Override
    public void addTv(Tv tv) {
        repo.save(tv);
    }

    @Override
    public void addTvList(List<Tv> tvs) {
        repo.saveAll(tvs);
    }

    @Override
    public List<TvDTO> getTvsForHomepage(Pageable pageable) {
        return null;
    }

    @Override
    public List<TvDTO> getTvsForHomepageByGenres(List<Genre> genres, Pageable pageable) {
        return null;
    }

    @Override
    public Metadata<List<TvDTO>> getSimilarTvs(String tvId, Pageable pageable) {
        return null;
    }

    @Override
    public List<TvDTO> getTvByGenre(Genre genre, Pageable pageable) {
        return null;
    }
}

package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.controller.TvController;
import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.dto.TvDetails;
import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.mapper.TvMapper;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.Tv;
import com.az.io.movieapi.repo.TvRepo;
import com.az.io.movieapi.service.TvService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<TvDTO> tvDTOS = TvMapper.convertValue(repo.findDistinctByGenresIn(genres, pageable));
        tvDTOS.forEach(tvDTO ->
                tvDTO.setLink(linkTo(methodOn(TvController.class)
                        .getTvById(tvDTO.getId()))
                        .toString()));
        return tvDTOS;
    }

    @Override
    public Metadata<List<TvDTO>> getSimilarTvs(String tvId, Pageable pageable) {
        return null;
    }

    @Override
    public List<TvDTO> getTvByGenre(Genre genre, Pageable pageable) {
        List<TvDTO> tvDTOS = TvMapper.convertValue(repo.findDistinctByGenres_Id(genre.getId(),pageable));
        tvDTOS.forEach(tvDTO ->
                tvDTO.setLink(linkTo(methodOn(TvController.class)
                        .getTvById(tvDTO.getId()))
                        .toString()));
        return tvDTOS;
    }
}

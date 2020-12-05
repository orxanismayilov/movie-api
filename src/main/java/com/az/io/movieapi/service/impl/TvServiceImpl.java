package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.controller.TvController;
import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.dto.TvDetails;
import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.mapper.TvMapper;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.Season;
import com.az.io.movieapi.model.Tv;
import com.az.io.movieapi.repo.TvRepo;
import com.az.io.movieapi.service.SeasonService;
import com.az.io.movieapi.service.TvService;
import com.az.io.movieapi.util.LinkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class TvServiceImpl implements TvService {

    private final TvRepo repo;
    private final SeasonService seasonService;

    @Override
    public Tv getTvEntityById(String imdbId) {
        return repo.findByImdbId(imdbId).orElseThrow(NotFoundException::new);
    }

    @Override
    public TvDetails getTvDetailsByImdbId(String imdbId) {
        Optional<Tv> tv = repo.findByImdbId(imdbId);
        if (tv.isPresent()){
            TvDetails tvDetails = TvMapper.convertDetails(tv.get());
            tvDetails.setMetadata(getSimilarTvs(imdbId, PageRequest.of(0, 30)));
            return tvDetails;
        }else throw new NotFoundException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTv(Tv tv) {
        Set<Season> seasons = tv.getSeasons();
        tv.setSeasons(null);
        tv.getCast().forEach(cast -> cast.setTvs(Collections.singleton(tv)));
        tv.getGenres().forEach(genre -> genre.setTvs(Collections.singletonList(tv)));
        tv.getNetworks().forEach(tvNetwork -> tvNetwork.setTv(Collections.singleton(tv)));
        tv.getKeywords().forEach(keyword -> keyword.setTvs(Collections.singletonList(tv)));
        repo.save(tv);
        seasonService.addSeasons(seasons, tv);
    }

    @Override
    public void addTvList(List<Tv> tvs) {
        repo.saveAll(tvs);
    }

    @Override
    public List<TvDTO> getTvsForHomepage(Pageable pageable) {
        List<TvDTO> allTvDto = repo.findAllTvDto(pageable);
        allTvDto.forEach(tvDTO -> {
            tvDTO.setLanguages(Collections.singletonList("en"));
            tvDTO.setLink(linkTo(methodOn(TvController.class)
                    .getTvById(tvDTO.getImdbId()))
                    .toString());
        });
        return allTvDto;
    }

    @Override
    public List<TvDTO> getTvsForHomepageByGenres(List<Genre> genres, Pageable pageable) {
       /* List<TvDTO> tvDTOs = repo.findDistinctByGenresIn(genres, pageable);
        tvDTOs.forEach(tvDTO -> {
            tvDTO.setLanguages(Collections.singletonList("en"));
            tvDTO.setLink(linkTo(methodOn(TvController.class)
                    .getTvById(tvDTO.getImdbId()))
                    .toString());
        });
        return tvDTOs;*/
        return null;
    }

    @Override
    public Metadata<List<TvDTO>> getSimilarTvs(String tvId, Pageable pageable) {
        List<TvDTO> movieDTOS = TvMapper.convertValue(repo
                .findDistinctByKeywordsIn(tvId, pageable));
        movieDTOS.forEach(tvDTO ->
                tvDTO.setLink(linkTo(methodOn(TvController.class)
                        .getTvById(tvDTO.getImdbId()))
                        .toString()));
        return Metadata.<List<TvDTO>>builder()
                .movies(movieDTOS)
                .title("Similar Tv-series")
                .nextPage(LinkUtil.nextPageSimilarMovies(tvId, pageable))
                .responseType(1)
                .build();
    }

    @Override
    public List<TvDTO> getTvByGenre(Genre genre, Pageable pageable) {
        List<TvDTO> tvDTOS = TvMapper.convertValue(repo.findDistinctByGenres_Id(genre.getId(), pageable));
        tvDTOS.forEach(tvDTO ->
                tvDTO.setLink(linkTo(methodOn(TvController.class)
                        .getTvById(tvDTO.getImdbId()))
                        .toString()));
        return tvDTOS;
    }
}

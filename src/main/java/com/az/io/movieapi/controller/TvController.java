package com.az.io.movieapi.controller;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.dto.TvDetails;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.ResponseObject;
import com.az.io.movieapi.model.Tv;
import com.az.io.movieapi.service.GenreService;
import com.az.io.movieapi.service.TvService;
import com.az.io.movieapi.util.LinkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tv")
@RequiredArgsConstructor
public class TvController {

    private final TvService service;
    private final GenreService genreService;

    @GetMapping
    public ResponseObject<Metadata<List<TvDTO>>> getTvs(Pageable pageable) {
        List<TvDTO> tvDTOS = service.getTvsForHomepage(pageable);
        return ResponseObject.getSuccessResponse(Metadata.<List<TvDTO>>builder()
                .data(tvDTOS)
                .nextPage(LinkUtil.nextPageForTvs(pageable))
                .build());
    }

    @GetMapping("/{tvId}")
    public ResponseObject<TvDetails> getTvById(@PathVariable("tvId") String tvId) {
        return ResponseObject.getSuccessResponse(service.getTvDetailsById(tvId));
    }


    @GetMapping("/genres")
    public ResponseObject<Metadata<List<TvDTO>>> getTvsByGenre(@RequestParam List<String> genres, Pageable pageable) {
        List<Genre> genreList=genres.stream().map(genreService::getGenreByName).collect(Collectors.toList());
        return ResponseObject.getSuccessResponse(Metadata.<List<TvDTO>>builder()
                .nextPage(LinkUtil.nextPageTvsByGenre(genres,pageable))
                .data(service.getTvsForHomepageByGenres(genreList,pageable))
                .build());
    }

    @GetMapping("/similar/{tvId}")
    public ResponseObject<?> getSimilarTvs(@PathVariable("tvId") String movieId,Pageable pageable) {
        return ResponseObject.getSuccessResponse(service.getSimilarTvs(movieId,pageable));
    }

    @GetMapping("/search")
    public ResponseObject<List<MovieDTO>> search(@RequestParam("q") String q) {
        return ResponseObject.getSuccessResponse(service.searchByTitle(q));
    }

    @PostMapping
    public void AddMovie(@RequestBody Tv tv) {
        service.addTv(tv);
    }

    @PostMapping("/list")
    public void addMovieList(@RequestBody List<Tv> tvs) {
        service.addTvList(tvs);
    }

}

package com.az.io.movieapi.controller;

import com.az.io.movieapi.enums.GenreType;
import com.az.io.movieapi.model.Genre;
import com.az.io.movieapi.model.ResponseObject;
import com.az.io.movieapi.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/movie/list")
    public ResponseObject<List<Genre>> getMovieGenres() {
        List<Genre> genres=genreService.getGenreByType(GenreType.MOVIE.getCode());
        return ResponseObject.getSuccessResponse(genres);
    }

    @GetMapping("/tv/list")
    public ResponseObject<List<Genre>> getTvGenres() {
        List<Genre> genres=genreService.getGenreByType(GenreType.TV.getCode());
        return ResponseObject.getSuccessResponse(genres);
    }
}

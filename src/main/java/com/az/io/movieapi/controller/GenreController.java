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
}

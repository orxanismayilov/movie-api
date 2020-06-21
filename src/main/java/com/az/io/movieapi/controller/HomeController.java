package com.az.io.movieapi.controller;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.model.Metadata;
import com.az.io.movieapi.model.ResponseObject;
import com.az.io.movieapi.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public ResponseObject<List<Metadata<List<MovieDTO>>>> getHomePage(){
        List<Metadata<List<MovieDTO>>> homePage=homeService.getHomePage();
        return ResponseObject.getSuccessResponse(homePage);
    }

    @GetMapping("/tv")
    public ResponseObject<?> getTvHomePage(){
        return null;
    }
}

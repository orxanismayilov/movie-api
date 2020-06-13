package com.az.io.movieapi.controller;

import com.az.io.movieapi.model.Keyword;
import com.az.io.movieapi.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keywords")
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @PostMapping("/list")
    public void addKeywordList(@RequestBody List<Keyword> keywords) {
        keywordService.addKeywordList(keywords);
    }
}

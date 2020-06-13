package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.Keyword;
import com.az.io.movieapi.repo.KeywordRepo;
import com.az.io.movieapi.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepo repo;

    @Override
    public void addKeywordList(List<Keyword> keywords) {
        repo.saveAll(keywords);
    }
}

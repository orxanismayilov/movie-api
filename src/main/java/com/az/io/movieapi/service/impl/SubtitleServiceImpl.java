package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.projections.SubtitleProjection;
import com.az.io.movieapi.repo.SubtitleRepo;
import com.az.io.movieapi.service.SubtitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubtitleServiceImpl implements SubtitleService {

    private final SubtitleRepo subtitleRepo;

    @Override
    public List<SubtitleProjection> getSubtitlesByMovieId(String movieId) {
        return subtitleRepo.findAllByMovieId(movieId);
    }
}

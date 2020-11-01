package com.az.io.movieapi.service;

import com.az.io.movieapi.projections.SubtitleProjection;

import java.util.List;

public interface SubtitleService {

    List<SubtitleProjection> getSubtitlesByMovieId(String movieId);
}

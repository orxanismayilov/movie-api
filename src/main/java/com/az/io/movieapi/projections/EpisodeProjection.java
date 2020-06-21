package com.az.io.movieapi.projections;

import com.az.io.movieapi.mapper.TvVideoProjection;

import java.util.List;

public interface EpisodeProjection {

    List<TvVideoProjection> getVideos();
}

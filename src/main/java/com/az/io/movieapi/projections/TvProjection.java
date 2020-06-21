package com.az.io.movieapi.projections;

import java.util.List;

public interface TvProjection {

    String getImdbId();
    String getName();
    String getPosterPath();
    float getImdbRating();
    List<EpisodeProjection> getEpisodes();
}

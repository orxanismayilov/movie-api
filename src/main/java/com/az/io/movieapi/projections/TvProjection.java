package com.az.io.movieapi.projections;

public interface TvProjection {

    String getImdbId();
    String getName();
    String getPosterPath();
    float getImdbRating();
}

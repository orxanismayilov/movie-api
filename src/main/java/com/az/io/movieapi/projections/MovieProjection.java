package com.az.io.movieapi.projections;

import java.util.List;

public interface MovieProjection {

    String getImdbId();
    String getTitle();
    String getPosterPath();
    float getImdbRating();

    List<VideoProjection> getVideos();
}

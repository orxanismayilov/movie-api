package com.az.io.movieapi.projections;

import com.az.io.movieapi.model.Video;

import java.util.List;

public interface MovieProjection {

    String getImdbId();
    String getTitle();
    String getPosterPath();
    float getImdbRating();

    List<VideoProjection> getVideos();
}

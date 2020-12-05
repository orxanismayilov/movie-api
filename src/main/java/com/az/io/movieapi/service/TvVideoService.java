package com.az.io.movieapi.service;

import com.az.io.movieapi.model.Episode;
import com.az.io.movieapi.model.TvVideo;

import java.util.Set;

public interface TvVideoService {

    void addVideos(Set<TvVideo> videos, Episode episode);
}

package com.az.io.movieapi.service;

import com.az.io.movieapi.model.Episode;
import com.az.io.movieapi.model.TvSubtitle;

import java.util.Set;

public interface TvSubtitleService {

    void addSubtitles(Set<TvSubtitle> subtitles, Episode episode);
}

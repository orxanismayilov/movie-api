package com.az.io.movieapi.service;

import com.az.io.movieapi.model.Episode;
import com.az.io.movieapi.model.Season;

import java.util.Set;

public interface EpisodeService {

    void addEpisodes(Set<Episode> episodes, Season season);
}

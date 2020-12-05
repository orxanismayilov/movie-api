package com.az.io.movieapi.service;

import com.az.io.movieapi.model.Season;
import com.az.io.movieapi.model.Tv;

import java.util.Set;

public interface SeasonService {
    void addSeasons(Set<Season> seasons, Tv tv);
}

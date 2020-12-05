package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.Episode;
import com.az.io.movieapi.model.Season;
import com.az.io.movieapi.model.Tv;
import com.az.io.movieapi.repo.SeasonRepo;
import com.az.io.movieapi.service.EpisodeService;
import com.az.io.movieapi.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepo seasonRepo;
    private final EpisodeService episodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSeasons(Set<Season> seasons, Tv tv) {
        for (Season season : seasons) {
            Set<Episode> episodes = season.getEpisodes();
            season.setTv(tv);
            season.setEpisodes(null);
            seasonRepo.save(season);
            episodeService.addEpisodes(episodes,season);
        }
    }
}

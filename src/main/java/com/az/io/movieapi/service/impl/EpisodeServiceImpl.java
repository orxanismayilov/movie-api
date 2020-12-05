package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.Episode;
import com.az.io.movieapi.model.Season;
import com.az.io.movieapi.model.TvSubtitle;
import com.az.io.movieapi.model.TvVideo;
import com.az.io.movieapi.repo.EpisodeRepo;
import com.az.io.movieapi.service.EpisodeService;
import com.az.io.movieapi.service.TvSubtitleService;
import com.az.io.movieapi.service.TvVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepo episodeRepo;
    private final TvVideoService videoService;
    private final TvSubtitleService subtitleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addEpisodes(Set<Episode> episodes, Season season) {
        for (Episode episode : episodes) {
            Set<TvVideo> videos = episode.getVideos();
            Set<TvSubtitle> subtitles = episode.getSubtitleList();
            episode.setSeason(season);
            episode.setVideos(null);
            episode.setSubtitleList(null);
            episodeRepo.save(episode);
            videoService.addVideos(videos, episode);
            subtitleService.addSubtitles(subtitles,episode);
        }
    }
}

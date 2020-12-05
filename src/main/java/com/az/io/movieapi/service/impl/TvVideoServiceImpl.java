package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.Episode;
import com.az.io.movieapi.model.TvVideo;
import com.az.io.movieapi.repo.TvVideoRepo;
import com.az.io.movieapi.service.TvVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TvVideoServiceImpl implements TvVideoService {

    private final TvVideoRepo repo;

    @Override
    public void addVideos(Set<TvVideo> videos, Episode episode) {
        videos.forEach(video->video.setEpisode(episode));
        repo.saveAll(videos);
    }
}

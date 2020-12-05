package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.Episode;
import com.az.io.movieapi.model.TvSubtitle;
import com.az.io.movieapi.repo.TvSubtitleRepo;
import com.az.io.movieapi.service.TvSubtitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TvSubtitleServiceImpl implements TvSubtitleService {

    private final TvSubtitleRepo subtitleRepo;

    @Override
    public void addSubtitles(Set<TvSubtitle> subtitles, Episode episode) {
        subtitles.remove(null);
        subtitles.forEach(subtitle -> subtitle.setEpisode(episode));
        subtitleRepo.saveAll(subtitles);
    }
}

package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.dto.VideoDTO;
import com.az.io.movieapi.exception.NotFoundException;
import com.az.io.movieapi.mapper.VideoMapper;
import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.model.Video;
import com.az.io.movieapi.repo.VideoRepo;
import com.az.io.movieapi.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepo repo;

    @Override
    public void addVideo(Video video) {
        repo.save(video);
    }

    @Override
    public VideoDTO getVideoByMovieId(String movieId, String language) {
        return VideoMapper.convertValue(repo.findByMovie_ImdbIdAndLanguage(movieId, language).orElseThrow(NotFoundException::new));
    }

    @Override
    public void updateVideo(Video video) {

    }

    @Override
    public void deleteVideoById() {

    }

    @Override
    public void addVideoList(Collection<Video> videos) {
        repo.saveAll(videos);
    }

    @Override
    public boolean isVideoExists(Movie movie, String language) {
        return repo.findAllByMovieAndLanguage(movie, language).size() != 0;
    }
}

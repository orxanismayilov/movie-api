package com.az.io.movieapi.service;

import com.az.io.movieapi.dto.VideoDTO;
import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.model.Video;

import java.util.List;

public interface VideoService {

    void addVideo(Video video);

    VideoDTO getVideoByMovieId(String movieId, String language);

    void updateVideo(Video video);

    void deleteVideoById();

    void addVideoList(List<Video> videos);

    boolean isVideoExists(Movie movie, String language);
}

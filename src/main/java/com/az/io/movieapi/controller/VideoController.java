package com.az.io.movieapi.controller;

import com.az.io.movieapi.dto.VideoDTO;
import com.az.io.movieapi.model.ResponseObject;
import com.az.io.movieapi.model.Video;
import com.az.io.movieapi.service.SubtitleService;
import com.az.io.movieapi.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final SubtitleService subtitleService;

    @GetMapping("/{movieId}/{language}")
    public ResponseObject<VideoDTO> getVideo(@PathVariable("movieId") String movieId
            , @PathVariable("language") String language) {
        VideoDTO video = videoService.getVideoByMovieId(movieId, language);
        video.setSubtitle(subtitleService.getSubtitlesByMovieId(movieId));
        return ResponseObject.getMovieSuccessResponse(video);
    }

    @PostMapping
    public void addVideo(@RequestBody Video video) {
        videoService.addVideo(video);
    }

    @PostMapping("/list")
    public void addVideo(@RequestBody List<Video> video) {
        videoService.addVideoList(video);
    }
}

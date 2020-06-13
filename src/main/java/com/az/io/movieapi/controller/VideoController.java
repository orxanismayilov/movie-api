package com.az.io.movieapi.controller;

import com.az.io.movieapi.dto.VideoDTO;
import com.az.io.movieapi.model.ResponseObject;
import com.az.io.movieapi.model.Video;
import com.az.io.movieapi.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/{movieId}/{language}")
    public ResponseObject<VideoDTO> getVideo(@PathVariable("movieId") String movieId
                                                 , @PathVariable("language") String language) {
        return ResponseObject.getSuccessResponse(videoService.getVideoByMovieId(movieId,language));
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

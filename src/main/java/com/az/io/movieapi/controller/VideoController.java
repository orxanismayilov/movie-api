package com.az.io.movieapi.controller;

import com.az.io.movieapi.dto.VideoWithSubtitleDTO;
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
    public ResponseObject<VideoWithSubtitleDTO> getVideo(@PathVariable("movieId") String movieId
            , @PathVariable("language") String language) {
        VideoWithSubtitleDTO videoWithSubtitleDTO = new VideoWithSubtitleDTO();
        videoWithSubtitleDTO.setSubtitles(subtitleService.getSubtitlesByMovieId(movieId));
        videoWithSubtitleDTO.setVideoDTO(videoService.getVideoByMovieId(movieId, language));
        return ResponseObject.getSuccessResponse(videoWithSubtitleDTO);
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

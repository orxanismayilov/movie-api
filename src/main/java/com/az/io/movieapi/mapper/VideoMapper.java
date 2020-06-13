package com.az.io.movieapi.mapper;

import com.az.io.movieapi.controller.VideoController;
import com.az.io.movieapi.dto.VideoDTO;
import com.az.io.movieapi.dto.VideoLink;
import com.az.io.movieapi.model.Video;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class VideoMapper {

    public static VideoDTO convertValue(Video video){
        VideoDTO dto=new VideoDTO();
        dto.setPath(video.getVideoPath());
        dto.setReferrer(video.getReferer());
        return dto;
    }

    public static List<VideoDTO> convertValue(List<Video> videos){
        return videos.stream().map(VideoMapper::convertValue).collect(Collectors.toList());
    }

    public static VideoLink videoLinkMapper(Video video){
        VideoLink videoLink=new VideoLink();
        videoLink.setLanguage(video.getLanguage());
        videoLink.setLink(linkTo(methodOn(VideoController.class).getVideo(video.getMovie().getImdbId(),video.getLanguage())).toString());
        return videoLink;
    }

    public static List<VideoLink> videoLinkMapper(Set<Video> videos){
        return videos.stream().map(VideoMapper::videoLinkMapper).collect(Collectors.toList());
    }
}

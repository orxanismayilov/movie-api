package com.az.io.movieapi.dto;

import com.az.io.movieapi.projections.SubtitleProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {

    private String referrer;
    private String path;
    private List<SubtitleProjection> subtitle;
}

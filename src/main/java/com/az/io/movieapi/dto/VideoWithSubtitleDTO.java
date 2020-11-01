package com.az.io.movieapi.dto;

import com.az.io.movieapi.projections.SubtitleProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoWithSubtitleDTO {

    private VideoDTO videoDTO;
    private List<SubtitleProjection> subtitles;
}

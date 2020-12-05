package com.az.io.movieapi.dto;

import com.az.io.movieapi.model.TvSubtitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeDetail {

    private String title;
    private int number;
    private Date airDate;
    private String backdropPath;
    private String overview;
    private float imdbRating;
    private String videoUrl;
    private List<TvSubtitleDetail> subtitles;
}

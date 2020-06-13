package com.az.io.movieapi.dto;

import com.az.io.movieapi.model.Metadata;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetails {

    private String imdbId;
    private String title;
    private float popularity;
    private String overview;
    private float imdbRating;
    private Date releaseDate;
    private float runtime;
    private String posterPath;
    private String backdropPath;
    private String trailerPath;
    private List<String> genres;
    private List<String> companies;
    private List<String> cast;
    private List<String> director;
    private List<String> writer;
    private List<VideoLink> videos;
    private Metadata metadata;
    private List<String> countries;
    private List<CommentDTO> comments;

}

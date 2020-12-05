package com.az.io.movieapi.dto;

import com.az.io.movieapi.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TvDetails {

    private String imdbId;
    private String title;
    private String overview;
    private int numberOfSeasons;
    private String releaseDate;
    private String posterPath;
    private String trailerPath;
    private String backdropPath;
    private float imdbRating;
    private List<SeasonDTO> seasons;
    private List<String> genres;
    private List<String> director;
    private List<String> cast;
    private List<String> countries;
    private List<String> companies;
    private Metadata metadata;
}

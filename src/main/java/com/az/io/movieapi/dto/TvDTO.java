package com.az.io.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TvDTO {
    private String imdbId;
    private String name;
    private String posterPath;
    private List<String> languages;
    private float imdbRating;
    private String link;

    public TvDTO(String imdbId, String name, String posterPath, float imdbRating) {
        this.imdbId = imdbId;
        this.name = name;
        this.posterPath = posterPath;
        this.imdbRating = imdbRating;
    }
}

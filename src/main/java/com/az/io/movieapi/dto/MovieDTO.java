package com.az.io.movieapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class MovieDTO {
    private String id;
    private String title;
    private String posterPath;
    private List<String> languages;
    private float imdbRating;
    private String link;

    public MovieDTO(String id, String title, String posterPath, float imdbRating) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.imdbRating = imdbRating;
    }
}

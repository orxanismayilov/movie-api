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

    private String id;
    private String title;
    private String posterPath;
    private List<String> languages;
    private float imdbRating;
    private String link;
}

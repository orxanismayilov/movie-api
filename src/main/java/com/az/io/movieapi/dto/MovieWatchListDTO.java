package com.az.io.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieWatchListDTO {
    private String id;
    private String title;
    private String posterPath;
    private List<String> languages;
    private float imdbRating;
    private String link;
    private Date insertDate;
}

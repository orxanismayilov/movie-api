package com.az.io.movieapi.dto;

import com.az.io.movieapi.model.Metadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailsDTO {

    private MovieDetails movieDetails;
    private List<Metadata> metadata;
}

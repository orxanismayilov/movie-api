package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Metadata<T> {

    private T movies;
    private String title;
    private String nextPage;
    private int responseType;
}

package com.az.io.movieapi.model;

import com.az.io.movieapi.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Metadata<T> {

    private T data;
    private String title;
    private String nextPage;
}

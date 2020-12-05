package com.az.io.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDTO {

    private long id;
    private int number;
    private List<EpisodeDetail> episodes;
}

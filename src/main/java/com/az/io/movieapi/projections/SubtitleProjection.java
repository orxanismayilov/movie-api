package com.az.io.movieapi.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface SubtitleProjection {

    @JsonProperty("lang")
    String getLanguage();

    String getPath();
}

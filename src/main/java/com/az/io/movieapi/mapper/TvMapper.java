package com.az.io.movieapi.mapper;

import com.az.io.movieapi.dto.TvDTO;
import com.az.io.movieapi.projections.TvProjection;

import java.util.List;
import java.util.stream.Collectors;

public class TvMapper {

    public static TvDTO convertValue(TvProjection projection) {
        TvDTO tvDTO=new TvDTO();
        tvDTO.setId(projection.getImdbId());
        tvDTO.setImdbRating(projection.getImdbRating());
        tvDTO.setLanguages(null);
        tvDTO.setLink("");
        tvDTO.setPosterPath(projection.getPosterPath());
        return tvDTO;
    }

    public static List<TvDTO> convertValue(List<TvProjection> projections) {
        return projections.stream().map(TvMapper::convertValue).collect(Collectors.toList());
    }
}

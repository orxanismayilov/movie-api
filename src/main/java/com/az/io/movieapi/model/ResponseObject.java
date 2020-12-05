package com.az.io.movieapi.model;

import com.az.io.movieapi.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject<T> {
    private String status;
    private int responseType;
    private T response;

    public static <T> ResponseObject<T> getMovieSuccessResponse(T t) {
        return new ResponseObject<>("success", ResponseType.MOVIE.getOrdinal(), t);
    }

    public static <T> ResponseObject<T> getMovieFailResponse(T t) {
        return new ResponseObject<>("Fail", ResponseType.MOVIE.getOrdinal(), t);
    }

    public static <T> ResponseObject<T> getTvSuccessResponse(T t) {
        return new ResponseObject<>("success", ResponseType.TV.getOrdinal(), t);
    }

    public static <T> ResponseObject<T> getTvFailResponse(T t) {
        return new ResponseObject<>("Fail", ResponseType.TV.getOrdinal(), t);
    }
}

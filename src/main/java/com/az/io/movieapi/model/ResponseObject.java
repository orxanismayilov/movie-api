package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject<T> {
    private String status;
    private T response;

    public static  <T> ResponseObject<T> getSuccessResponse(T t) {
        return new ResponseObject<>("success",t);
    }

    public static  <T> ResponseObject<T>  getFailResponse(T t) {
        return new ResponseObject<>("Fail",t);
    }
}

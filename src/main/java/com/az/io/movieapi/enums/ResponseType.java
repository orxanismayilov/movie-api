package com.az.io.movieapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum ResponseType {

    MOVIE(0, "movie"),
    TV(1, "tv-serises");

    private final int ordinal;
    private final String desc;

    private static final Map<Integer, ResponseType> enumMap;

    static {
        enumMap = Arrays.stream(ResponseType.values()).collect(Collectors.toMap(ResponseType::getOrdinal, e -> e));
    }

    public static ResponseType getValue(int ordinal) {
        return enumMap.get(ordinal);
    }

    public static boolean hasValue(int ordinal) {
        return enumMap.containsKey(ordinal);
    }
}

package com.az.io.movieapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public enum GenreType {
    MOVIE(1),
    TV(2);

    private int code;

    public static GenreType valueOf(int statusCode) {
        GenreType type = resolve(statusCode);
        if (type == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return type;
        }
    }

    @Nullable
    public static GenreType resolve(int code) {
        GenreType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            GenreType type = var1[var3];
            if (type.code == code) {
                return type;
            }
        }

        return null;
    }
}

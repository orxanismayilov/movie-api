package com.az.io.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String id;
    private String displayName;
    private String givenName;
    private String familyName;
    private String googleId;
    private String profilePhoto;
}

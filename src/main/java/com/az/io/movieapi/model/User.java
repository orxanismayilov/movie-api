package com.az.io.movieapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String displayName;
    private String givenName;
    private String familyName;
    private String googleId;
    private String profilePhoto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "history_id")
    private History history;

}

package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movie_watchlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieWatchlist {

    @Id
    @GeneratedValue
    private int id;
    private Date insertDate;
    private boolean status;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    @JoinColumn(name = "movie_id")
    private Movie movie;
}

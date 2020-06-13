package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "movie_history")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieHistory {
    @Id
    @GeneratedValue
    private int id;
    private Date insertDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    @JoinColumn(name = "history_id")
    private History history;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    @JoinColumn(name = "movie_id")
    private Movie movie;
}

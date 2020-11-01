package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "subtitle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subtitle {

    @Id
    @GeneratedValue
    private int id;
    private String language;
    private String path;
    private String movieId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "movie_id")
    private Movie movie;
}

package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tv")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tv {

    @Id
    private String imdbId;
    private String name;
    private String originalName;
    private String overview;
    private float popularity;
    private String posterPath;
    private String trailerPath;
    private String backdropPath;
    private float imdbRating;
    private boolean status;

    @OneToMany(mappedBy = "tv")
    private Set<Season> seasons;

    @ManyToMany
    @JoinTable(
            name = "tv_genre",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    @JoinTable(
            name = "tv_keyword",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private Set<Keyword> keywords;
}

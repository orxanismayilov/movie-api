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
    private long id;
    private String imdbId;
    private String name;
    private String overview;
    private float popularity;
    private int numberOfSeasons;
    private String releaseDate;
    private String posterPath;
    private String trailerPath;
    private String backdropPath;
    private float imdbRating;
    private boolean status;
    private boolean inProduction;

    @OneToMany(mappedBy = "tv", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private Set<Season> seasons;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            })
    @JoinTable(
            name = "tv_genre",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

   @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            })
    @JoinTable(
            name = "tv_keyword",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private Set<Keyword> keywords;

   @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            })
    @JoinTable(
            name = "tv_crew",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "crew_id"))
    private Set<Crew> crew;

   @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            })
    @JoinTable(
            name = "tv_cast",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id"))
    private Set<Cast> cast;

   @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            })
    @JoinTable(
            name = "tv_network",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "network_id"))
    private Set<TvNetwork> networks;

   @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            })
    @JoinTable(
            name = "tv_country",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries;

   @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            })
    @JoinTable(
            name = "tv_company",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> companies;
}

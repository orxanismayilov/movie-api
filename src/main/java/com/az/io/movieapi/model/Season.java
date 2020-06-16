package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "season")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Season {

    @Id
    @GeneratedValue
    private int id;
    private Date airDate;
    private String name;
    private String overview;
    private String posterPath;
    private int seasonNumber;
    private int episodeCount;

    @OneToMany(mappedBy = "season")
    private Set<Episode> episodes;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_id")
    private Tv tv;

}

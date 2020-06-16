package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "episode")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Episode {

    @Id
    private int id;
    private Date airDate;
    private int episodeNumber;
    private String name;
    private String overview;
    private String stillPath;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    private Season season;

    @OneToMany(mappedBy = "episode")
    private Set<TvVideo> videos;
}

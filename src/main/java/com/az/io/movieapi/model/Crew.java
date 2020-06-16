package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crew {
    @Id
    @GeneratedValue
    private int id;
    private String department;
    private String job;
    private String name;
    private String profilePath;

    @ManyToMany(mappedBy = "crew")
    private List<Movie> movie;

    @ManyToMany(mappedBy = "crew")
    private Set<Tv> tvs;
}

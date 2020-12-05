package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    private int id;
    private String name;
    private boolean status;

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    private List<Movie> movies;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            },
            mappedBy = "genres")
    private List<Tv> tvs;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

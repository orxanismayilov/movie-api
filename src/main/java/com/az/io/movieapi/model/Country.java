package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    private String iso_3166_1;
    private String name;

    @ManyToMany(mappedBy = "countries",cascade = CascadeType.ALL)
    private List<Movie> movies;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH
            },
            mappedBy = "countries")
    private List<Tv> tvs;
}

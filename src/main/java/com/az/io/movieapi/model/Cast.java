package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cast {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "_character")
    private String character;
    private String name;
    @Column(name = "_order")
    private int order;
    private String profilePath;

    @ManyToMany(mappedBy = "cast")
    private List<Movie> movie;
}

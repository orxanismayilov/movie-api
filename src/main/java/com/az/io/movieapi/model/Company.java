package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "companies",cascade = CascadeType.ALL)
    private List<Movie> movies;
}

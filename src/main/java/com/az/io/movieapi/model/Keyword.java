package com.az.io.movieapi.model;


import jdk.internal.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "keyword")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keyword {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "keywords",fetch = FetchType.LAZY)
    private List<Movie> movies;
}

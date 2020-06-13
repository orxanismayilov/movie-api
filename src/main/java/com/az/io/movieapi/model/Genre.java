package com.az.io.movieapi.model;

import com.az.io.movieapi.enums.GenreType;
import lombok.*;

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
    private int type;
    private boolean status;

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    private List<Movie> movies;

    public Genre(int id, String name, GenreType genreType) {
        this.id=id;
        this.name=name;
        this.type=genreType.getCode();
    }

    public Genre(int id, String name, GenreType genreType, boolean status) {
        this.id=id;
        this.name=name;
        this.type=genreType.getCode();
        this.status=status;
    }

    public Genre(int id, String name, int type) {
        this.id=id;
        this.name=name;
        this.type=type;
    }

    public GenreType getType() {
        return GenreType.valueOf(this.type);
    }

    public void setType(GenreType type) {
        this.type=type.getCode();
    }

    public Genre(int id,String name) {
        this.id=id;
        this.name=name;
    }
}

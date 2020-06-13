package com.az.io.movieapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vote")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    private String id;
    private float voteCount;
    private float voteAverage;
    private boolean status;

    @OneToOne(mappedBy = "vote",cascade = CascadeType.ALL)
    private Movie movie;
}

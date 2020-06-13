package com.az.io.movieapi.model;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "watch_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue
    private int id;
    private Date watchDate;
    private boolean status;

    @OneToOne(mappedBy = "history")
    private User user;

    @OneToMany(mappedBy = "history")
    private Set<MovieHistory> movieHistories;

    @PrePersist
    public void prePersist() {
        this.watchDate = Date.from(Instant.now());
    }
}

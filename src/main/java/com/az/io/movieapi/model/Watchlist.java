package com.az.io.movieapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "watch_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Watchlist {
    @Id
    @GeneratedValue
    private int id;
    private Date insertDate;
    private boolean status;

    @OneToOne(mappedBy = "watchlist",cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "watchlist")
    private Set<MovieWatchlist> movieWatchlist;

    @PrePersist
    public void prePersist(){
        insertDate=new Date();
    }
}

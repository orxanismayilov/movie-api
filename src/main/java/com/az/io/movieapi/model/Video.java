package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    private String id;
    private String language;
    private String referer;
    private String videoPath;
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "movie_id")
    private Movie movie;
}

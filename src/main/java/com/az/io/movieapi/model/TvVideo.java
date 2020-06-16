package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tv_video")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TvVideo {

    @Id
    private String id;
    private String language;
    private String referer;
    private String videoPath;
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "episode_id")
    private Episode episode;
}

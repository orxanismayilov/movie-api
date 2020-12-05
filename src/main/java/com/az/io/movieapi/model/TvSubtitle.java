package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tv_subtitle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TvSubtitle {

    @Id
    private String id;
    private String language;
    private String path;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "episode_id")
    private Episode episode;
}

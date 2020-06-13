package com.az.io.movieapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private String id;
    private Date commentDate;
    private String commentText;
    private boolean spoiler;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}

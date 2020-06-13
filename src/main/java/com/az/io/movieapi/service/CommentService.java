package com.az.io.movieapi.service;

import com.az.io.movieapi.model.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    List<Comment> getComments(String movieId);

    void deleteComment(String commentId);

    Comment updateComment(Comment comment);

}

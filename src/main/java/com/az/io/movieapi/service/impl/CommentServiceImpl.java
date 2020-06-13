package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.Comment;
import com.az.io.movieapi.repo.CommentRepo;
import com.az.io.movieapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Override
    public void addComment(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public List<Comment> getComments(String movieId) {
        return null;
    }

    @Override
    public void deleteComment(String commentId) {
        commentRepo.deleteById(commentId);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepo.save(comment);
    }
}

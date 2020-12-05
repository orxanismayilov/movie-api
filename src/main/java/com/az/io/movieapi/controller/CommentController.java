package com.az.io.movieapi.controller;

import com.az.io.movieapi.model.Comment;
import com.az.io.movieapi.model.Movie;
import com.az.io.movieapi.model.ResponseObject;
import com.az.io.movieapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{movieId}")
    public ResponseObject<List<Comment>> getCommentsByMovieId(@PathVariable("movieId") String movieId) {
        return ResponseObject.getMovieSuccessResponse(commentService.getComments(movieId));
    }

    @PostMapping("/{movieId}")
    public ResponseObject<?> addComment(@PathVariable("movieId") String movieId,@RequestBody Comment comment) {
        comment.setMovie(new Movie(movieId));
        commentService.addComment(comment);
        return ResponseObject.getMovieSuccessResponse(null);
    }

    @DeleteMapping("/{commentId}")
    public ResponseObject<?> deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
        return ResponseObject.getMovieSuccessResponse(null);
    }

    @PutMapping("/update")
    public ResponseObject<?> updateComment(@RequestBody Comment comment) {
        return ResponseObject.getMovieSuccessResponse(commentService.updateComment(comment));
    }
}

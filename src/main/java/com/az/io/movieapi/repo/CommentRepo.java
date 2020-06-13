package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,String> {
}

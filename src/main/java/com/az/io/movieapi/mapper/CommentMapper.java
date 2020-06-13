package com.az.io.movieapi.mapper;

import com.az.io.movieapi.dto.CommentDTO;
import com.az.io.movieapi.model.Comment;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentMapper {

    public static List<CommentDTO> convertValue(Set<Comment> comments){
        return comments.stream().map(CommentMapper::convertValue).collect(Collectors.toList());
    }

    public static CommentDTO convertValue(Comment comment){
        CommentDTO dto=new CommentDTO();
        dto.setCommentDate(comment.getCommentDate());
        dto.setCommentText(comment.getCommentText());
        dto.setId(comment.getId());
        dto.setSpoiler(comment.isSpoiler());
        return dto;
    }
}

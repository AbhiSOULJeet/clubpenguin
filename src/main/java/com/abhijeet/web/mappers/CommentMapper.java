package com.abhijeet.web.mappers;

import com.abhijeet.web.dto.CommentDto;
import com.abhijeet.web.models.Comment;

public class CommentMapper {
    public static Comment mapToComment(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .username(commentDto.getUsername())
                .comt(commentDto.getComt())
                .event(commentDto.getEvent())
                .build();
        return comment;
    }

    public static CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = CommentDto.builder()
                .username(comment.getUsername())
                .comt(comment.getComt())
                .event(comment.getEvent())
                .build();
        return commentDto;
    }

}

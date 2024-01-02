package com.abhijeet.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhijeet.web.dto.CommentDto;

@Service
public interface CommentService {
    // void saveComment(Long eventId, CommentDto commentDto);

    List<CommentDto> findAllByEventId(Long eventId);

    Long findMaxCommentId();

    void saveComment(Long eventId, CommentDto commentDto);
}

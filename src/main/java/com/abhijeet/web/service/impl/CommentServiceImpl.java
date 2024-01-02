package com.abhijeet.web.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhijeet.web.dto.CommentDto;
import com.abhijeet.web.mappers.CommentMapper;
import com.abhijeet.web.mappers.EventMapper;
import com.abhijeet.web.models.Club;
import com.abhijeet.web.models.Comment;
import com.abhijeet.web.models.CommentRepository;
import com.abhijeet.web.models.Event;
import com.abhijeet.web.models.EventRepository;
import com.abhijeet.web.service.CommentService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EventRepository eventRepository;

    public CommentServiceImpl(CommentRepository commentRepository, EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void saveComment(Long eventId, CommentDto commentDto) {
        Event event = eventRepository.findById(eventId).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setEvent(event);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllByEventId(Long eventId) {
        List<Comment> comments = commentRepository.searchCommentsByEventId(eventId);
        return comments.stream().map(comment -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
    }

    @Override
    public Long findMaxCommentId() {
        return commentRepository.findMaxId();
    }

}
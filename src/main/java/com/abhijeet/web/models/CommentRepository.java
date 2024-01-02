package com.abhijeet.web.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.abhijeet.web.dto.CommentDto;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    CommentDto save(CommentDto commentDto);

    @Query(value = "select * from comment c where c.eventid=:eventId", nativeQuery = true)
    List<Comment> searchCommentsByEventId(Long eventId);

    @Query(value = "select max(c.id) from comment c", nativeQuery = true)
    Long findMaxId();
}

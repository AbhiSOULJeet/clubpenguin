package com.abhijeet.web.dto;

import lombok.ToString;

import com.abhijeet.web.models.Event;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ToString
public class CommentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String comt;
    private Event event;

}

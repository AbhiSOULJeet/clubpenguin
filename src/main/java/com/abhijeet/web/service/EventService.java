package com.abhijeet.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhijeet.web.dto.EventDto;

@Service
public interface EventService {

    void saveEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void updateEvent(EventDto eventDto);

    void deleteById(Long eventId);
}

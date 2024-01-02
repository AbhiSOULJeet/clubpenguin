package com.abhijeet.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abhijeet.web.dto.EventDto;
import com.abhijeet.web.models.Club;
import com.abhijeet.web.models.ClubRepository;
import com.abhijeet.web.models.Event;
import com.abhijeet.web.models.EventRepository;
import com.abhijeet.web.security.SecurityUtil;
import com.abhijeet.web.service.EventService;
import com.abhijeet.web.mappers.EventMapper;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void saveEvent(Long clubId, EventDto eventDto) {
        // finding club by ID so that we can tie it to our event table
        Club club = clubRepository.findById(clubId).get();
        Event event = EventMapper.mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);

    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventMapper.mapToEventDto(event)).collect(Collectors.toList());

    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return EventMapper.mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = EventMapper.mapToEvent(eventDto);
        eventRepository.save(event);

    }

    @Override
    public void deleteById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

}

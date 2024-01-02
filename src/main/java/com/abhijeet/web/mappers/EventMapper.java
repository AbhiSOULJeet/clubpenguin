package com.abhijeet.web.mappers;

import java.util.stream.Collectors;

import com.abhijeet.web.dto.EventDto;
import com.abhijeet.web.models.Event;

public class EventMapper {

    public static Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .club(eventDto.getClub())
                .build();

        return event;
    }

    public static EventDto mapToEventDto(Event event) {
        EventDto eventDto = EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .club(event.getClub())
                .build();

        return eventDto;
    }
}

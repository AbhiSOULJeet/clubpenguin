package com.abhijeet.web.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.abhijeet.web.dto.EventDto;
import java.util.List;

@EnableJpaRepositories
public interface EventRepository extends JpaRepository<Event, Long> {
    EventDto save(EventDto eventDto);

    // List<Event> findAll();
}

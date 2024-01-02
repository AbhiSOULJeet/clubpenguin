package com.abhijeet.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.abhijeet.web.dto.ClubDto;
import com.abhijeet.web.dto.CommentDto;
import com.abhijeet.web.dto.EventDto;
import com.abhijeet.web.dto.RegistrationDto;
import com.abhijeet.web.mappers.EventMapper;
import com.abhijeet.web.models.Comment;
import com.abhijeet.web.models.Event;
import com.abhijeet.web.security.SecurityUtil;
import com.abhijeet.web.service.CommentService;
import com.abhijeet.web.service.EventService;
import com.abhijeet.web.service.UserService;

import groovyjarjarantlr4.v4.codegen.model.ModelElement;
import jakarta.validation.Valid;
import javassist.bytecode.stackmap.BasicBlock.Catch;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller

public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private CommentService commentService;

    public EventController(EventService eventService, CommentService commentService) {
        this.eventService = eventService;
        this.commentService = commentService;
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        EventDto event = eventService.findByEventId(eventId);
        try {
            List<CommentDto> comment = commentService.findAllByEventId(eventId);
            model.addAttribute("comment", comment);
            model.addAttribute("event", event);
        } catch (Exception e) {
            String ex = e.toString();
            System.out.println(ex);
        }
        return "event-details";
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);

        return "event-edit";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId, @ModelAttribute("event") EventDto eventDto,
            Model model) {
        model.addAttribute("event", eventDto);
        eventService.deleteById(eventId);
        return "redirect:/events";
    }

    @PostMapping("/events/{eventId}/edit")
    public String saveEventForm(@PathVariable("eventId") Long eventId,
            @ModelAttribute("event") EventDto eventDto,
            BindingResult result, Model model) {
        // if (result.hasErrors()) {
        model.addAttribute("event", eventDto);
        // return "event-edit";
        // }
        EventDto event = eventService.findByEventId(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(event.getClub());
        eventService.updateEvent(eventDto);

        return "redirect:/clubs";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto,
            Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events-create";
        }
        eventService.saveEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId + "/events";
    }

    // @GetMapping("/events/{eventId}/comment")
    // public String viewComments(@PathVariable("eventId") EventDto eventDto, Model
    // model) {
    // List<CommentDto> comments =
    // commentService.findAllByEventId(eventDto.getId());
    // model.addAttribute("comments", comments);
    // return "/events/" + eventDto.getId();
    // }

    // @GetMapping("/events/{eventId}/comment")
    // public String addComments(@PathVariable("eventId") Long eventId, CommentDto
    // comment,
    // Model model) {
    // EventDto event = eventService.findByEventId(eventId);
    // model.addAttribute("event", event);
    // model.addAttribute("comment", comment);
    // return "/events/" + eventId;

    // }

    @PostMapping("/events/{eventId}/comment")
    public String saveComments(@PathVariable("eventId") Long eventId, @ModelAttribute("comment") CommentDto comment,
            Model model) {
        Long maxId = commentService.findMaxCommentId();
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        comment.setId(maxId + 1);
        comment.setUsername(SecurityUtil.getSessionuser());
        commentService.saveComment(eventId, comment);
        model.addAttribute("comment", comment);

        return "redirect:/events/" + eventId;
    }

}

package com.hyzertags.controllers;

import com.hyzertags.domains.Event;
import com.hyzertags.repositories.EventRepository;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link Event}.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * {@code POST  /events} : Create a new event.
     */
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
    }

    /**
     * {@code PUT  /events/:id} : Updates an existing event.
     */
    @PutMapping("/events/{eventID}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventID, @RequestBody Event event) {
        // TODO add service layer for put?
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.OK);
    }

    /**
     * {@code GET  /events} : get all the events.
     */
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
    }

    /**
     * {@code GET  /events/:id} : get the "id" event.
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return new ResponseEntity<>(eventRepository.findById(id).orElseGet(Event::new), HttpStatus.OK);
    }

    /**
     * {@code DELETE  /events/:id} : delete the "id" event.
     */
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Boolean> deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

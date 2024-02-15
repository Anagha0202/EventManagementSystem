package com.evntmgmt.EventManagement.controllers;

import com.evntmgmt.EventManagement.models.Events;
import com.evntmgmt.EventManagement.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/v1/events")
public class EventsController {
    @Autowired
    private EventsService eventsService;
    @GetMapping
    public ResponseEntity<List<Events>> getAllEvents() {
        return new ResponseEntity<List<Events>>(eventsService.allEvents(), HttpStatus.OK);
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<Optional<Events>> getEvent(@PathVariable Integer eventId) {
        return new ResponseEntity<Optional<Events>>(eventsService.oneEvent(eventId), HttpStatus.OK);
    }
    @PostMapping("/createEvent")
    public ResponseEntity<Events> createEvent(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Events>(eventsService.createEvent(Integer.valueOf(payload.get("eventID")), payload.get("eventName"), payload.get("eventDateTime"), payload.get("eventVenue")), HttpStatus.OK);
    }
    @DeleteMapping("/removeEvent/{eventId}")
    public ResponseEntity<Boolean> removeEvent(@PathVariable Integer eventId) {
        return new ResponseEntity<Boolean>(eventsService.removeEvent(eventId), HttpStatus.OK);
    }
    @PostMapping("/updateEvent/{eventId}")
    public ResponseEntity<Events> updateEvent(@PathVariable Integer eventId, @RequestBody Map<String, String> payload) {
        return new ResponseEntity<Events>(eventsService.updateEvent(eventId, payload.get("eventName"), payload.get("eventDateTime"), payload.get("eventVenue")), HttpStatus.OK);
    }
}

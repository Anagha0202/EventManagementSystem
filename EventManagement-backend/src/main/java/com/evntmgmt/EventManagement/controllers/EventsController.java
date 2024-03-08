package com.evntmgmt.EventManagement.controllers;

import com.evntmgmt.EventManagement.dto.EventsDto;
import com.evntmgmt.EventManagement.models.Events;
import com.evntmgmt.EventManagement.response.GeneralResponse;
import com.evntmgmt.EventManagement.services.EventsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/events")
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
    public GeneralResponse createEvent(@Valid @RequestBody EventsDto eventsDto) {
        return eventsService.createEvent(eventsDto.getEventName(), eventsDto.getEventDesc(), eventsDto.getEventDateTime(), eventsDto.getEventVenue(), eventsDto.getPriceGold(), eventsDto.getPriceSilver(), eventsDto.getPriceBronze());
    }
    @DeleteMapping("/removeEvent/{eventId}")
    public GeneralResponse removeEvent(@PathVariable Integer eventId) {
        return eventsService.removeEvent(eventId);
    }
    @PutMapping("/updateEvent/{eventId}")
    public ResponseEntity<Events> updateEvent(@PathVariable Integer eventId, @RequestBody Map<String, String> payload) {
        return new ResponseEntity<Events>(eventsService.updateEvent(eventId, payload.get("eventName"), payload.get("eventDateTime"), payload.get("eventVenue"), payload.get("eventDesc"), payload.get("priceGold"), payload.get("priceSilver"), payload.get("priceBronze")), HttpStatus.OK);
    }
}

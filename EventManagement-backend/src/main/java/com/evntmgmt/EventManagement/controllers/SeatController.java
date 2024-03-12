package com.evntmgmt.EventManagement.controllers;

import com.evntmgmt.EventManagement.models.SeatDetails;
import com.evntmgmt.EventManagement.models.Seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.evntmgmt.EventManagement.services.SeatsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    private SeatsService seatsService;
    @GetMapping("/{eventId}")
    public ResponseEntity<Seats> getAllSeats(@PathVariable Integer eventId) {
        return new ResponseEntity<Seats>(seatsService.allSeats(eventId), HttpStatus.OK);
    }
    @GetMapping("/{eventId}/{seatId}")
    public ResponseEntity<SeatDetails> getOneSeatDetails(@PathVariable Integer eventId, @PathVariable String seatId) {
        return new ResponseEntity<SeatDetails>(seatsService.getSeatDetails(eventId, seatId), HttpStatus.OK);
    }
}

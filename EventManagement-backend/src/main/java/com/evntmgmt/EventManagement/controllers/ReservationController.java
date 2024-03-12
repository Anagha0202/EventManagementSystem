package com.evntmgmt.EventManagement.controllers;

import com.evntmgmt.EventManagement.dto.ReservationDto;
import com.evntmgmt.EventManagement.models.Reservation;
import com.evntmgmt.EventManagement.response.GeneralResponse;
import com.evntmgmt.EventManagement.services.ReservationService;
import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return new ResponseEntity<List<Reservation>>(reservationService.getAllReservations(), HttpStatus.OK);
    }
    @GetMapping("/{reservationId}")
    public ResponseEntity<Optional<Reservation>> getOneReservation(@PathVariable ObjectId reservationId) {
        return new ResponseEntity<Optional<Reservation>>(reservationService.getOneReservation(reservationId), HttpStatus.OK);
    }
//    @GetMapping("/{email}")
//    public ResponseEntity<Optional<Reservation>> getReservationByEmail(@PathVariable String email) {
//        return new ResponseEntity<Optional<Reservation>>(reservationService.getByEmail(email), HttpStatus.OK);
//    }
    @PostMapping("/createReservation")
    public GeneralResponse createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        return reservationService.createReservation(reservationDto.getEmail(), reservationDto.getEventId(), reservationDto.getSeatIds());
    }
}

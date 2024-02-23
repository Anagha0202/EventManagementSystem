package com.evntmgmt.EventManagement.services;

import com.evntmgmt.EventManagement.models.Reservation;
import com.evntmgmt.EventManagement.models.Seats;
import com.evntmgmt.EventManagement.repository.ReservationRepository;
import com.evntmgmt.EventManagement.repository.SeatsRepository;
import com.evntmgmt.EventManagement.response.GeneralResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    SeatsRepository seatsRepository;
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    public Optional<Reservation> getOneReservation(ObjectId reservationId) {
        return reservationRepository.findById(reservationId);
    }
    public Optional<Reservation> getByEmail(String email) {
        return reservationRepository.findByEmail(email);
    }
    public GeneralResponse createReservation(String email, Integer eventId, List<String> seatIds) {
        int countSeatsAvailable = 0;
        float total = (float) 0;
        try {
            for (String seatid : seatIds) {
                Seats seat = seatsRepository.findSeatsByEventIdAndSeatId(eventId, seatid);
                System.out.println(seat);
                if (seat.getSeats().get(0).getIsAvailable()) {
                    countSeatsAvailable += 1;
                    total += seat.getSeats().get(0).getPrice();
                } else {
                    return new GeneralResponse("Seat ID: " + seatid + " not available", false);
                }
            }
        } catch(Exception e) {
            return  new GeneralResponse("Exception: "+e, false);
        }

        boolean isPaid = false;
        Reservation reservation = reservationRepository.insert(new Reservation(email, eventId, seatIds, total, isPaid));
        return new GeneralResponse(countSeatsAvailable+" seats reserved successfully! Reservation Id: "+reservation.getReservationId(), true, (String) reservation.getReservationId());
    }
}

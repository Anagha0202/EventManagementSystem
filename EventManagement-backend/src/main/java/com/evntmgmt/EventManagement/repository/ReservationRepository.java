package com.evntmgmt.EventManagement.repository;

import com.evntmgmt.EventManagement.models.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, ObjectId> {
    Optional<Reservation> findByEmail(String email);
}

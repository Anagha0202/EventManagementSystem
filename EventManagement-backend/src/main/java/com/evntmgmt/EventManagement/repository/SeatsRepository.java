package com.evntmgmt.EventManagement.repository;

import com.evntmgmt.EventManagement.models.SeatDetails;
import com.evntmgmt.EventManagement.models.Seats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SeatsRepository extends MongoRepository<Seats, ObjectId> {
    Seats findSeatByEventId(Integer eventId);
}

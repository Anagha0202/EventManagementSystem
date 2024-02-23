package com.evntmgmt.EventManagement.repository;

import com.evntmgmt.EventManagement.models.Seats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SeatsRepository extends MongoRepository<Seats, Integer> {
    Seats findSeatByEventId(Integer eventId);
    @Query(value = "{ '_id': ?0, 'seats.seatId': ?1 }", fields = "{ 'seats.$': 1, '_id': 0 }")
    Seats findSeatsByEventIdAndSeatId(Integer eventId, String seatId);
}

package com.evntmgmt.EventManagement.repository;

import com.evntmgmt.EventManagement.models.Events;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventsRepository extends MongoRepository<Events, ObjectId> {
    Optional<Events> findEventByEventId(Integer eventId);
}

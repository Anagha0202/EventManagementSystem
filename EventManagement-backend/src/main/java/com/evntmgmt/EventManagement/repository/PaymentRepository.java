package com.evntmgmt.EventManagement.repository;

import com.evntmgmt.EventManagement.models.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Reservation, ObjectId> {
}

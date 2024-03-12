package com.evntmgmt.EventManagement.repository;

import com.evntmgmt.EventManagement.models.SeatDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatDetailsRepository extends MongoRepository<SeatDetails,String> {
}

package com.evntmgmt.EventManagement.services;

import com.evntmgmt.EventManagement.models.Seats;
import com.evntmgmt.EventManagement.repository.SeatsRepository;
import com.evntmgmt.EventManagement.response.GeneralResponse;
import com.mongodb.client.result.DeleteResult;
import com.evntmgmt.EventManagement.models.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.evntmgmt.EventManagement.repository.EventsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {
    final Integer noOfGold = 5;
    final Integer noOfSilver = 10;
    final Integer noOfBronze = 20;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private SeatsRepository seatsRepository;
    @Autowired
    private SeatsService seatsService;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Events> allEvents() {
        return eventsRepository.findAll();
    }
    public Optional<Events> oneEvent(Integer eventId){
        return eventsRepository.findEventByEventID(eventId);
    }
    public GeneralResponse createEvent(Integer eventId, String eventName, String eventDateTime, String eventVenue, Float priceGold, Float priceSilver, Float priceBronze) {
        Optional<Events> existingEvent = eventsRepository.findEventByEventID(eventId);
        if(existingEvent.isPresent()) {
            return new GeneralResponse("Event ID already exists", false);
        }
        Events event = eventsRepository.insert(new Events(eventId, eventName, eventDateTime, eventVenue));
        mongoTemplate.save(event, "Events");

        Seats seats = seatsRepository.insert(new Seats(eventId, seatsService.createSeats(noOfGold,priceGold,noOfSilver,priceSilver,noOfBronze,priceBronze)));
        mongoTemplate.save(seats, "Seats");
        return new GeneralResponse("Event created successfully!", true);
    }
    public Boolean removeEvent(Integer eventId) {
        try{
            DeleteResult deleteResult = mongoTemplate.remove(new Query(Criteria.where("eventID").is(eventId)), Events.class, "Events");
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    public Events updateEvent(Integer eventId, String eventName, String eventDateTime, String eventVenue) {
        Update update = new Update();
        update.set("eventName", eventName);
        update.set("eventDateTime", eventDateTime);
        update.set("eventVenue", eventVenue);
        return mongoTemplate.findAndModify(new Query(Criteria.where("eventID").is(eventId)), update, Events.class);
    }
}

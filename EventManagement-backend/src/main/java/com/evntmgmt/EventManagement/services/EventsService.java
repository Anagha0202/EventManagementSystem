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
import java.util.Random;

@Service
public class EventsService {
    final Integer noOfGold = 10;
    final Integer noOfSilver = 12;
    final Integer noOfBronze = 12;
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
        return eventsRepository.findEventByEventId(eventId);
    }
    public GeneralResponse createEvent(String eventName, String eventDesc, String eventDateTime, String eventVenue, Float priceGold, Float priceSilver, Float priceBronze) {
        Random r = new Random();
        int low = 19;
        int high = 1000000000;
        Integer eventId = r.nextInt(high-low) + low;

        Optional<Events> existingEvent = eventsRepository.findEventByEventId(eventId);
        if(existingEvent.isPresent()) {
            return new GeneralResponse("Event ID already exists. Please try again.", false);
        }
        Events event = eventsRepository.insert(new Events(eventId, eventName, eventDesc, eventDateTime, eventVenue, priceGold, priceSilver, priceBronze));
        mongoTemplate.save(event, "Events");

        Seats seats = seatsRepository.insert(new Seats(eventId, seatsService.createSeats(noOfGold,priceGold,noOfSilver,priceSilver,noOfBronze,priceBronze)));
        mongoTemplate.save(seats, "Seats");
        return new GeneralResponse("Event created successfully!", true);
    }
    public GeneralResponse removeEvent(Integer eventId) {
        try{
            List<Events> foundEvent = mongoTemplate.find(new Query(Criteria.where("eventId").is(eventId)), Events.class);
            List<Seats> foundSeats = mongoTemplate.find(new Query(Criteria.where("eventId").is(eventId)), Seats.class);
            if(!foundEvent.isEmpty() && !foundSeats.isEmpty()) {
                DeleteResult deleteEventResult = mongoTemplate.remove(new Query(Criteria.where("eventId").is(eventId)), Events.class, "Events");
                DeleteResult deleteSeatsResult = mongoTemplate.remove(new Query(Criteria.where("eventId").is(eventId)), Seats.class, "Seats");
                return new GeneralResponse("Event Deleted Successfully!", true);
            }
            return new GeneralResponse("Unable to delete as Event or Seats not found", false);
        } catch(Exception e) {
            return new GeneralResponse(e.getMessage(), false);
        }
    }
    public Events updateEvent(Integer eventId, String eventName, String eventDateTime, String eventVenue, String eventDesc, String priceGold, String priceSilver, String priceBronze) {
        float pricegold = Float.parseFloat(priceGold);
        float pricesilver = Float.parseFloat(priceSilver);
        float pricebronze = Float.parseFloat(priceBronze);
        Update update = new Update();
        update.set("eventName", eventName);
        update.set("eventDateTime", eventDateTime);
        update.set("eventVenue", eventVenue);
        update.set("eventDesc", eventDesc);
        update.set("priceGold", pricegold);
        update.set("priceSilver", pricesilver);
        update.set("priceBronze", pricebronze);
        return mongoTemplate.findAndModify(new Query(Criteria.where("eventId").is(eventId)), update, Events.class);
    }
}

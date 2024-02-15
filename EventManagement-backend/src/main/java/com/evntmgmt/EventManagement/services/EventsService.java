package com.evntmgmt.EventManagement.services;

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
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Events> allEvents() {
        return eventsRepository.findAll();
    }
    public Optional<Events> oneEvent(Integer eventId){
        return eventsRepository.findEventByEventID(eventId);
    }
    public Events createEvent(Integer eventId, String eventName, String eventDateTime, String eventVenue) {
        Events event = eventsRepository.insert(new Events(eventId, eventName, eventDateTime, eventVenue));

        mongoTemplate.save(event, "Events");
        return event;
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

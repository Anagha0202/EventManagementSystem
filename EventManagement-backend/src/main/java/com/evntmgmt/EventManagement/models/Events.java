package com.evntmgmt.EventManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {

    private Object ID;
    @Id
    private Integer eventID;
    private String eventName;
    private String eventDateTime;
    private String eventVenue;

    public Events(Integer eventId, String eventName, String eventDateTime, String eventVenue) {
        this.eventID = eventId;
        this.eventName = eventName;
        this.eventDateTime = eventDateTime;
        this.eventVenue = eventVenue;
    }
}

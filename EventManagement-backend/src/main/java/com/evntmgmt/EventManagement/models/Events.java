package com.evntmgmt.EventManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private Float priceGold;
    private Float priceSilver;
    private Float priceBronze;
    public Events(Integer eventId, String eventName, String eventDateTime, String eventVenue) {
        this.eventID = eventId;
        this.eventName = eventName;
        this.eventDateTime = eventDateTime;
        this.eventVenue = eventVenue;
    }

    public Events(Integer eventId, String eventName, String eventDateTime, String eventVenue, Float priceGold, Float priceSilver, Float priceBronze) {
        this.eventID = eventId;
        this.eventName = eventName;
        this.eventDateTime = eventDateTime;
        this.eventVenue = eventVenue;
        this.priceGold = priceGold;
        this.priceSilver = priceSilver;
        this.priceBronze = priceBronze;
    }
}

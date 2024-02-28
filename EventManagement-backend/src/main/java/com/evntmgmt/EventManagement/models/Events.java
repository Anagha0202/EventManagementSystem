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
    private String eventDesc;
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

    public Events(Integer eventId, String eventName, String eventDesc, String eventDateTime, String eventVenue, Float priceGold, Float priceSilver, Float priceBronze) {
        this.eventID = eventId;
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventDateTime = eventDateTime;
        this.eventVenue = eventVenue;
        this.priceGold = priceGold;
        this.priceSilver = priceSilver;
        this.priceBronze = priceBronze;
    }

    public Integer getEventID() {
        return eventID;
    }
    public String getEventName() {
        return eventName;
    }
    public String getEventDateTime() {
        return eventDateTime;
    }
    public String getEventVenue() {
        return eventVenue;
    }
    public Float getPriceGold() {
        return priceGold;
    }
    public Float getPriceSilver() {
        return priceSilver;
    }
    public Float getPriceBronze() {
        return priceBronze;
    }
    public String getEventDesc() {
        return eventDesc;
    }
    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }
    public void setPriceGold(Float priceGold) {
        this.priceGold = priceGold;
    }
    public void setPriceSilver(Float priceSilver) {
        this.priceSilver = priceSilver;
    }
    public void setPriceBronze(Float priceBronze) {
        this.priceBronze = priceBronze;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }
}

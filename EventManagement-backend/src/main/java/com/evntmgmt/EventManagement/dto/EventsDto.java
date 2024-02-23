package com.evntmgmt.EventManagement.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
public class EventsDto {
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

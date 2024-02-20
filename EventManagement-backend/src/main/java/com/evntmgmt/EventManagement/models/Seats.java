package com.evntmgmt.EventManagement.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Seats")
@Data
public class Seats {
    private Object ID;
    @Id
    private Integer eventId;
    private List<SeatDetails> seats;
    public Seats() {}
    public Seats(Integer eventId) {
        this.eventId = eventId;
    }
    public Seats(Integer eventId, List<SeatDetails> seats) {
        this.eventId = eventId;
        this.seats = seats;
    }
    public List<SeatDetails> getSeats() {
        return seats;
    }
    public void setSeats(List<SeatDetails> seats) {
        this.seats = seats;
    }
    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}

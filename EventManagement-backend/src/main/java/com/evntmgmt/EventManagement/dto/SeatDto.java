package com.evntmgmt.EventManagement.dto;

import com.evntmgmt.EventManagement.models.SeatDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    @Id
    private Integer eventId;
    private List<SeatDetails> seats;
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

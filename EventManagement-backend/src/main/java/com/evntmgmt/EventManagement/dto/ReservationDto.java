package com.evntmgmt.EventManagement.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    @Id
    private ObjectId reservationId;
    private String email;
    private Integer eventId;
    private List<String> seatIds;
    private Float total;
    private Boolean isPaid;
    private Date reservationDate;
    private Date paidDate;
    public void setReservationId(ObjectId reservationId) {
        this.reservationId = reservationId;
    }
    public ObjectId getReservationId() {
        return reservationId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public Integer getEventId() {
        return eventId;
    }
    public void setSeatIds(List<String> seatIds) {
        this.seatIds = seatIds;
    }
    public List<String> getSeatIds() {
        return seatIds;
    }
    public void setTotal(Float total) {
        this.total = total;
    }
    public Float getTotal() {
        return total;
    }
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
    public Boolean getIsPaid() {
        return isPaid;
    }
    public Date getPaidDate() {
        return paidDate;
    }
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }
    public Date getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}

package com.evntmgmt.EventManagement.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@NoArgsConstructor
@AllArgsConstructor
public class SeatDetailsDto {
    @Indexed(unique = true)
    private String seatId;
    private String seatClass;
    private float price;
    private boolean isAvailable;
    public String getSeatId(){
        return seatId;
    }
    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
    public String getSeatClass() {
        return seatClass;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}

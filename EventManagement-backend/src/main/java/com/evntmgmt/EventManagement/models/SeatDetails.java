package com.evntmgmt.EventManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Seats")
@Data
public class SeatDetails {
//    @Indexed(unique = true)
    private String seatId;
    private String seatClass;
    private float price;
    private boolean isAvailable;
    public SeatDetails() {}
    public SeatDetails(String seatId, String seatClass, float price, boolean isAvailable) {
        this.seatId = seatId;
        this.seatClass = seatClass;
        this.price = price;
        this.isAvailable = isAvailable;
    }
    public String getSeatId(){
        return seatId;
    }
    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }
    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }
    public boolean getIsAvailable() {
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

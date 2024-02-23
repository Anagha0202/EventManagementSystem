package com.evntmgmt.EventManagement.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
@NoArgsConstructor
public class GeneralResponse {
    private String message;
    private boolean isSuccessful;
    private String reservationId;
    public GeneralResponse(String message, boolean isSuccessful) {
        this.message = message;
        this.isSuccessful = isSuccessful;
    }
    public GeneralResponse(String message, boolean isSuccessful, String reservationId) {
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.reservationId = reservationId;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }
    public String getMessage() {
        return message;
    }
    public boolean getIsSuccessful() {
        return isSuccessful;
    }
    public String getReservationId() { return reservationId; }
}

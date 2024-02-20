package com.evntmgmt.EventManagement.response;

public class GeneralResponse {
    private String message;
    private boolean isSuccessful;
    public GeneralResponse(String message, boolean isSuccessful) {
        this.message = message;
        this.isSuccessful = isSuccessful;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
    public String getMessage() {
        return message;
    }
    public boolean isSuccessful() {
        return isSuccessful;
    }
}

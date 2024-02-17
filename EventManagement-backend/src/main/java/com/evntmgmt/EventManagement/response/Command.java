package com.evntmgmt.EventManagement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Command {
    private String message;
    private boolean isSuccessful;
    private String jwtToken;
    private boolean loginCredentialsIsAdmin;
    public Command(String message, boolean isSuccessful) {
        this.message = message;
        this.isSuccessful = isSuccessful;
    }
    public Command(String message, boolean isSuccessful, String jwtToken) {
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.jwtToken = jwtToken;
    }
    public Command(String message, boolean isSuccessful, String jwtToken, boolean loginCredentialsIsAdmin) {
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.jwtToken = jwtToken;
        this.loginCredentialsIsAdmin = loginCredentialsIsAdmin;
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
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    public String getJwtToken() {
        return jwtToken;
    }
    public boolean getLoginCredentialsIsAdmin() { return loginCredentialsIsAdmin; }

    public void setLoginCredentialsIsAdmin(boolean loginCredentialsIsAdmin) { this.loginCredentialsIsAdmin = loginCredentialsIsAdmin; }
}

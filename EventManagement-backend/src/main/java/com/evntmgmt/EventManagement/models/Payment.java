package com.evntmgmt.EventManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Reservations")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    private ObjectId reservationId;
    private String billingFirstName;
    private String billingLastName;
    private String billingStreetAddr;
    private String billingCity;
    private String billingState;
    private Integer billingZipcode;
    private String billingCountry;
    private String cardNumber;
    private String cardExpiry;
    private String cardCVV;
    private String cardHolderFullName;
    public ObjectId getReservationId() {
        return reservationId;
    }
    public String getBillingFirstName() {
        return billingFirstName;
    }
    public String getBillingLastName() {
        return billingLastName;
    }
    public String getBillingStreetAddr() {
        return billingStreetAddr;
    }
    public String getBillingCity() {
        return billingCity;
    }
    public String getBillingState() {
        return billingState;
    }
    public Integer getBillingZipcode() {
        return billingZipcode;
    }
    public String getBillingCountry() {
        return billingCountry;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getCardExpiry() {
        return cardExpiry;
    }
    public String getCardCVV() {
        return cardCVV;
    }
    public String getCardHolderFullName() {
        return cardHolderFullName;
    }
    public void setReservationId(ObjectId reservationId) {
        this.reservationId = reservationId;
    }
    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }
    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }
    public void setBillingStreetAddr(String billingStreetAddr) {
        this.billingStreetAddr = billingStreetAddr;
    }
    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }
    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }
    public void setBillingZipcode(Integer billingZipcode) {
        this.billingZipcode = billingZipcode;
    }
    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }
    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }
    public void setCardHolderFullName(String cardHolderFullName) {
        this.cardHolderFullName = cardHolderFullName;
    }
}

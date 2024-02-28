package com.evntmgmt.EventManagement.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    @Id
    private ObjectId reservationId;
//    @NotBlank(message = "First Name is required")
//    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
//    private String billingFirstName;
//    @NotBlank(message = "Last Name is required")
//    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
//    private String billingLastName;
//    @NotBlank(message = "Street Address is required")
//    private String billingStreetAddr;
//    @NotBlank(message = "City is required")
//    private String billingCity;
//    @NotBlank(message = "State is required")
//    private String billingState;
//    @NotBlank(message = "Zipcode is required")
//    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$")
//    private Integer billingZipcode;
//    @NotBlank(message = "Country is required")
//    private String billingCountry;
    @NotBlank(message = "Card Number is required")
    @Pattern(regexp = "^(?:\\d*?){13,16}$",
             message = "Enter valid card number")
    private String cardNumber;
    @NotBlank(message = "Card Expiry is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$",
             message = "Enter valid expiry date")
    private String cardExpiry;
    @NotBlank(message = "Card CVV/CVC is required")
    @Pattern(regexp = "^[0-9]{3,4}$",
             message = "Enter valid CVV")
    private String cardCVV;
    @NotBlank(message = "Full Name on Card is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$",
             message = "Enter Full Name as is on the card")
    private String cardHolderFullName;

    public ObjectId getReservationId() {
        return reservationId;
    }
//    public String getBillingFirstName() {
//        return billingFirstName;
//    }
//    public String getBillingLastName() {
//        return billingLastName;
//    }
//    public String getBillingStreetAddr() {
//        return billingStreetAddr;
//    }
//    public String getBillingCity() {
//        return billingCity;
//    }
//    public String getBillingState() {
//        return billingState;
//    }
//    public Integer getBillingZipcode() {
//        return billingZipcode;
//    }
//    public String getBillingCountry() {
//        return billingCountry;
//    }
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
//    public void setBillingFirstName(String billingFirstName) {
//        this.billingFirstName = billingFirstName;
//    }
//    public void setBillingLastName(String billingLastName) {
//        this.billingLastName = billingLastName;
//    }
//    public void setBillingStreetAddr(String billingStreetAddr) {
//        this.billingStreetAddr = billingStreetAddr;
//    }
//    public void setBillingCity(String billingCity) {
//        this.billingCity = billingCity;
//    }
//    public void setBillingState(String billingState) {
//        this.billingState = billingState;
//    }
//    public void setBillingZipcode(Integer billingZipcode) {
//        this.billingZipcode = billingZipcode;
//    }
//    public void setBillingCountry(String billingCountry) {
//        this.billingCountry = billingCountry;
//    }
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

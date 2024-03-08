package com.evntmgmt.EventManagement.services;

import com.evntmgmt.EventManagement.models.Events;
import com.evntmgmt.EventManagement.models.Reservation;
import com.evntmgmt.EventManagement.models.Seats;
import com.evntmgmt.EventManagement.repository.SeatsRepository;
import com.evntmgmt.EventManagement.response.GeneralResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PaymentService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private EmailService emailService;
   @Autowired
   private SeatsService seatsService;
   @Autowired
   private SeatsRepository seatsRepository;
    public GeneralResponse verifyPaymentDetails(ObjectId reservationId, String cardNumber, String cardExpiry, String cardCVV, String cardHolderFullName) {
//        verify card number and matching cvv
        System.out.println("cardNumber="+cardNumber);
        if (cardNumber.startsWith("3")) {
            if(cardCVV.length()!=4) {
                return new GeneralResponse("Please enter valid CVV for entered American Express Card", false);
            }
        } else if ((cardNumber.startsWith("4") || cardNumber.startsWith("5") || cardNumber.startsWith("6"))) {
            if(cardCVV.length()!=3) {
                return new GeneralResponse("Please enter valid CVV for entered Visa/Mastercard/Discover Card", false);
            }
        } else {
            return new GeneralResponse("We do not accept the entered card type. Please enter a different card details to complete payment", false);
        }

//        verify expiration date
        int month = 0;
        int year = 0;
        Pattern pattern = Pattern.compile("^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$");
        Matcher matcher = pattern.matcher(cardExpiry);
        while (matcher.find()) {
            month = Integer.parseInt(matcher.group(1));
            year = Integer.parseInt(matcher.group(2));
        }
        DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("MM/uu");
        String creditCardExpiryDateString = month+"/"+year;
        try {
            YearMonth lastValidMonth = YearMonth.parse(creditCardExpiryDateString, ccMonthFormatter);
            if (YearMonth.now(ZoneId.systemDefault()).isAfter(lastValidMonth)) {
                return new GeneralResponse("Credit card has expired. Please enter valid card expiration date.", false);
            }
        } catch (DateTimeParseException dtpe) {
            return new GeneralResponse ("Not a valid expiry date: " + creditCardExpiryDateString, false);
        }

        Update updateReservation = new Update();
        updateReservation.set("isPaid", true);
        updateReservation.set("paidDate", new Date());
        Reservation reservation = mongoTemplate.findAndModify(new Query(Criteria.where("reservationId").is(reservationId)), updateReservation, Reservation.class);
        assert reservation != null;
        String recipient = reservation.getEmail();
        Integer eventId = reservation.getEventId();
        Events event = (mongoTemplate.find(new Query(Criteria.where("eventId").is(eventId)), Events.class)).get(0);

        Seats seats = seatsService.updateSeatAvailability(eventId, reservation.getSeatIds());
        FindAndReplaceOptions options = new FindAndReplaceOptions().returnNew();
        Seats updatedSeats = mongoTemplate.findAndReplace(new Query(Criteria.where("eventId").is(eventId)), seats, options, Seats.class, "Seats", Seats.class);
        System.out.println(updatedSeats);
        try {
            String subject = "EventManagementApplication: Your ticket details for Event: "+event.getEventName();
            String body = "Your booking ID: "+reservationId+"\nEvent ID: "+eventId+"\nEvent Name: "+event.getEventName()+"\nEvent Description: "+event.getEventDesc()+"\nEvent Date and Time: "+event.getEventDateTime()+"\nEvent Venue: "+event.getEventVenue()+"\nSeats Booked: "+reservation.getSeatIds()+"\nYour total: "+reservation.getTotal();
            emailService.sendEmail(recipient, subject, body);
            return new GeneralResponse("Seats booked successfully! Your booking ID is " + reservationId, true);
        } catch (Exception e) {
            return new GeneralResponse("Error: "+e.getMessage(), false);
        }
    }
}

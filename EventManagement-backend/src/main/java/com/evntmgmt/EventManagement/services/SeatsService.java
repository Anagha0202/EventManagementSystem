package com.evntmgmt.EventManagement.services;

import com.evntmgmt.EventManagement.models.SeatDetails;
import com.evntmgmt.EventManagement.models.Seats;
import com.evntmgmt.EventManagement.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SeatsService {
    @Autowired
    private SeatsRepository seatsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Seats allSeats(Integer eventId) {
        return seatsRepository.findSeatByEventId(eventId);
    }
    public ArrayList<SeatDetails> createSeats(Integer noOfGold, Float priceGold, Integer noOfSilver, Float priceSilver, Integer noOfBronze, Float priceBronze) {
        ArrayList<SeatDetails> seatsDetailsForEvent = new ArrayList<>();
        for(int i=1; i<=noOfGold; i++) {
            SeatDetails seatDetails = new SeatDetails("G"+i, "Gold", priceGold, true);
            seatsDetailsForEvent.add(seatDetails);
        }
        for(int i=1; i<=noOfSilver; i++) {
            SeatDetails seatDetails = new SeatDetails("S"+i, "Silver", priceSilver, true);
            seatsDetailsForEvent.add(seatDetails);
        }
        for(int i=1; i<=noOfBronze; i++) {
            SeatDetails seatDetails = new SeatDetails("B"+i, "Bronze", priceBronze, true);
            seatsDetailsForEvent.add(seatDetails);
        }
        return seatsDetailsForEvent;
    }
    public SeatDetails getSeatDetails(Integer eventId, String seatId) {
        Seats seats = seatsRepository.findSeatByEventId(eventId);
        if(seats!=null) {
            List<SeatDetails> seatsDetails = seats.getSeats();
            for(SeatDetails sds : seatsDetails){
                if (Objects.equals(sds.getSeatId(), seatId))
                    return sds;
            }
        }
        return null;
    }
}

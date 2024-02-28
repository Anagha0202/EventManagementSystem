package com.evntmgmt.EventManagement.controllers;

import com.evntmgmt.EventManagement.dto.PaymentDto;
import com.evntmgmt.EventManagement.response.GeneralResponse;
import com.evntmgmt.EventManagement.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService  paymentService;
    @PostMapping
    public GeneralResponse confirmPayment(@Valid @RequestBody PaymentDto paymentDto) {
        return paymentService.verifyPaymentDetails(paymentDto.getReservationId(), paymentDto.getCardNumber(), paymentDto.getCardExpiry(), paymentDto.getCardCVV(), paymentDto.getCardHolderFullName());
    }
}

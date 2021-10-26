package com.sweethome.paymentservice.controller;

import com.sweethome.paymentservice.dto.Paymentdto;
import com.sweethome.paymentservice.entity.PaymentInfoEntity;
import com.sweethome.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PaymentController {
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    PaymentService paymentService;

    @PostMapping("/transaction")
    public int paymentDetails(@RequestBody Paymentdto bookingRequest) throws Exception {
        PaymentInfoEntity paymentInfo = PaymentService.savePaymenttransaction(bookingRequest);
        return paymentInfo.getTransactionId();
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<PaymentInfoEntity> paymentDetails() throws Exception {
        PaymentInfoEntity paymentInfo = paymentService.savePaymenttransaction();
        return new ResponseEntity<PaymentInfoEntity>(paymentInfo, HttpStatus.CREATED);
    }
}
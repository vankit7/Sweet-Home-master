package com.sweethome.Payment.controller;

import com.sweethome.Payment.dto.Paymentdto;
import com.sweethome.Payment.entity.TransactionDetailsEntity;
import com.sweethome.Payment.service.PaymentService;
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
    public Integer paymentDetails(@RequestBody Paymentdto paymentRequest) throws Exception {
        TransactionDetailsEntity transactionInfo = paymentService.savePaymenttransaction(paymentRequest);
        return transactionInfo.getTransactionId();
    }

   @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<TransactionDetailsEntity> paymentDetails(@PathVariable Integer transactionId) throws Exception {
       TransactionDetailsEntity transactionInfo = paymentService.getTransactionEntity(transactionId);
        return new ResponseEntity<TransactionDetailsEntity>(transactionInfo, HttpStatus.CREATED);
    }

   /* public ResponseEntity<Integer> paymentDetails(@RequestBody Paymentdto bookingRequest) throws Exception {
        TransactionDetailsEntity transactionInfo = paymentService.savePaymenttransaction(bookingRequest);
        return new ResponseEntity<Integer>(transactionInfo.getTransactionId(), HttpStatus.CREATED);
    }*/

}
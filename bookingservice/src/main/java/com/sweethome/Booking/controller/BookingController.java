package com.sweethome.Booking.controller;

import com.sweethome.Booking.exceptions.CustomException;
import com.sweethome.Booking.model.dto.BookingDto;
import com.sweethome.Booking.model.dto.PaymentDto;
import com.sweethome.Booking.model.entity.BookingInfoEntity;
import com.sweethome.Booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    BookingService bookingService;

    @PostMapping("/booking/{bookingId}/transaction")
    public ResponseEntity<BookingInfoEntity> doPayment(@RequestBody PaymentDto paymentDetails) throws Exception {
        BookingInfoEntity bookingInfo = bookingService.doPayment(paymentDetails);
        return new ResponseEntity<BookingInfoEntity>(bookingInfo, HttpStatus.CREATED);
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingInfoEntity> bookingDetails(@RequestBody BookingDto bookingRequest) throws CustomException {
        BookingInfoEntity bookingInfo = bookingService.bookingDetails(bookingRequest);
        return new ResponseEntity<BookingInfoEntity>(bookingInfo, HttpStatus.CREATED);
    }

}

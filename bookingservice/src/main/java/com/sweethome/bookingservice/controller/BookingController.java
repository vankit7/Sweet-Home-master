package com.sweethome.bookingservice.controller;

import com.sweethome.bookingservice.exceptions.CustomException;
import com.sweethome.bookingservice.model.dto.BookingDto;
import com.sweethome.bookingservice.model.dto.PaymentDto;
import com.sweethome.bookingservice.model.entity.BookingInfoEntity;
import com.sweethome.bookingservice.service.BookingService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<BookingInfoEntity> doPayment(@RequestBody PaymentDto paymentDetails) throws Exception, CustomException {
        BookingInfoEntity bookingInfo = bookingService.doPayment(paymentDetails);
        return new ResponseEntity<BookingInfoEntity>(bookingInfo, HttpStatus.CREATED);
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingInfoEntity> bookingDetails(@RequestBody BookingDto bookingRequest) throws Exception {
        BookingInfoEntity bookingInfo = bookingService.bookingDetails(bookingRequest);
        return new ResponseEntity<BookingInfoEntity>(bookingInfo, HttpStatus.CREATED);
    }
    @Controller
    public class MainController {

        @GetMapping("/")
        public void index() throws CustomException {
            throw new CustomException();
        }

    }
}

package com.sweethome.bookingservice.service;

import com.sweethome.bookingservice.dao.BookingDao;
import com.sweethome.bookingservice.exceptions.CustomException;
import com.sweethome.bookingservice.model.dto.BookingDto;
import com.sweethome.bookingservice.model.dto.CustomResponse;
import com.sweethome.bookingservice.model.dto.PaymentDto;
import com.sweethome.bookingservice.model.entity.BookingInfoEntity;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class BookingService {

    @Autowired
    public BookingService(BookingDao bookingDao, RestTemplate restTemplate, Producer<String, String> producer){
        this.bookingDao = bookingDao;
        this.restTemplate = restTemplate;
        this.producer = producer;
    }

    BookingDao bookingDao;
    RestTemplate restTemplate;
    Producer<String, String> producer;
    private int pricePerRoomPerDay = 1000;

    @Value("${url.service.payment}")
    private String paymentServiceUrl;

    public static ArrayList<String> getRoomNumbers(int numOfRooms){
        Random rand = new Random();
        int UpperBound = 100;
        ArrayList<String> roomList = new ArrayList<>();
        for (int i=0; i<numOfRooms; i++){
            roomList.add(String.valueOf(rand.nextInt(UpperBound)));
        }
        return roomList;
    }

    public BookingInfoEntity bookingDetails(BookingDto bookingRequest){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        int requestedNumofRooms = bookingRequest.getNumOfRooms();

        String roomNumbers = String.join(",",getRoomNumbers(requestedNumofRooms));
        BookingInfoEntity bookingInfo = new BookingInfoEntity();
        bookingInfo.setRoomNumbers(roomNumbers);
        bookingInfo.setFromDate(bookingRequest.getFromDate());
        bookingInfo.setToDate(bookingRequest.getToDate());
        bookingInfo.setBookedOn(date);
        bookingInfo.setAadharNumber(bookingRequest.getAadharNumber());
        long numberOfDays = (bookingInfo.getToDate().getTime() - bookingInfo.getFromDate().getTime())/(1000*60*60*24);
        System.out.println("Number of Days: "+numberOfDays);
        bookingInfo.setRoomPrice((int) (bookingRequest.getNumOfRooms()* pricePerRoomPerDay* numberOfDays));
        System.out.println(bookingInfo.toString());
        return bookingDao.save(bookingInfo);
    }

    public BookingInfoEntity doPayment(PaymentDto paymentDetails) throws Exception, CustomException {

        //call payment service and get paymentID to save booking
        System.out.println(paymentDetails.toString());
        String url = this.paymentServiceUrl + "/transaction";

        if (!(paymentDetails.getPaymentMode().trim().equalsIgnoreCase("UPI") || paymentDetails.getPaymentMode().trim().equalsIgnoreCase("CARD"))) {
            throw new CustomException("Invalid mode of payment");
        }
        int bookingId = paymentDetails.getBookingId();
        Optional<BookingInfoEntity> bookingInfoOptional = bookingDao.findById(bookingId);
        if(bookingInfoOptional.isPresent()) {
            BookingInfoEntity bookingInfo = bookingInfoOptional.get();
            int transactionId = restTemplate.postForObject(url, paymentDetails, Integer.class);
            bookingInfo.setTransactionId(transactionId);
            bookingDao.update(bookingInfo);
            String message = "Booking confirmed for user with aadhaar number: " + bookingInfo.getAadharNumber() +
                    " | " + " Here are the booking details: " + bookingInfo.toString();
            producer.send(new ProducerRecord<String, String>("message", "message", message));
            return bookingInfo;
        }
        else {
            throw new CustomException("Invalid Booking ID");
        }

    }
}

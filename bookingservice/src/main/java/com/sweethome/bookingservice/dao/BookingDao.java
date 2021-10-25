package com.sweethome.bookingservice.dao;

import com.sweethome.bookingservice.model.dto.PaymentDto;
import com.sweethome.bookingservice.model.entity.BookingInfoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDao {
    public Optional<BookingInfoEntity> findById(int bookingId) {
        return PaymentDto(bookingId).getBookingId();
    }

    public List<BookingInfoEntity> save(List<BookingInfoEntity> bookingInfoEntityList) {
        List<BookingInfoEntity> savedInfo = new ArrayList<>();
        for(BookingInfoEntity bookingInfoEntity : bookingInfoEntityList) {
            savedInfo.add(save(bookingInfoEntity));
        }
        return savedInfo;
    }

    public BookingInfoEntity save(BookingInfoEntity bookingInfoEntity) {
        return bookingInfoEntity;
    }
}

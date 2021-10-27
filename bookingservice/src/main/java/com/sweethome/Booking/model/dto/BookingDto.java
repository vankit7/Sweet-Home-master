package com.sweethome.Booking.model.dto;

import com.sun.istack.NotNull;

import java.util.Date;

public class BookingDto {
    @NotNull
    private Date fromDate;
    private Date toDate;
    private String aadharNumber;
    private int numOfRooms;
    public BookingDto() {
    }

    public String getAadharNumber(){
        return aadharNumber;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public Date getFromDate() { return fromDate; }

    public Date getToDate() { return toDate; }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public void setFromDate(Date fromDate){ this.fromDate = fromDate; }

    public void setToDate(Date toDate) { this.toDate = toDate; }

    public BookingDto(Date fromDate, Date toDate, String aadharNumber, int numOfRooms) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.aadharNumber = aadharNumber;
        this.numOfRooms = numOfRooms;
    }
}

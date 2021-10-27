package com.sweethome.Payment.entity;

import com.sun.istack.NotNull;


import javax.persistence.*;


@Table(name = "transaction")
@Entity
public class TransactionDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private String paymentMode;

    @NotNull
    private int bookingId;
    private String UpiId;
    private String CardNumber;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUpiId() {
        return UpiId;
    }

    public void setUpiId(String upiId) {
        UpiId = upiId;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "TransactionDetailsEntity{" +
                "transactionId=" + transactionId +
                ", paymentMode='" + paymentMode + '\'' +
                ", bookingId=" + bookingId +
                ", UpiId='" + UpiId + '\'' +
                ", CardNumber='" + CardNumber + '\'' +
                '}';
    }
}

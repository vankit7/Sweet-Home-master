package com.sweethome.paymentservice.service;

import com.sweethome.paymentservice.dao.Paymentdao;
import com.sweethome.paymentservice.dto.Paymentdto;
import com.sweethome.paymentservice.entity.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class PaymentService {

    @Autowired
    public PaymentService(Paymentdao paymentdao){
        this.paymentdao = paymentdao;

    }

    Paymentdao paymentdao;
    public TransactionDetailsEntity savePaymenttransaction(Paymentdto paymentdto){

        TransactionDetailsEntity transactionInfo = new TransactionDetailsEntity();
        transactionInfo.setPaymentMode(paymentdto.getPaymentMode());
        transactionInfo.setBookingId(paymentdto.getBookingId());
        transactionInfo.setUpiId(paymentdto.getUpiId());
        transactionInfo.setCardNumber(paymentdto.getCardNumber());
        return paymentdao.save(transactionInfo);
    }

    public TransactionDetailsEntity getTransactionEntity(int transactionId) {
        return paymentdao.findById(transactionId);
    }
}

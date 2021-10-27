package com.sweethome.Payment.service;

import com.sweethome.Payment.dao.Paymentdao;
import com.sweethome.Payment.dto.Paymentdto;
import com.sweethome.Payment.entity.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

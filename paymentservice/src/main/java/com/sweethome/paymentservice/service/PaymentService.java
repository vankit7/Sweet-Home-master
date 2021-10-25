package com.sweethome.paymentservice.service;

import com.sweethome.paymentservice.dao.Paymentdao;
import com.sweethome.paymentservice.dto.Paymentdto;
import com.sweethome.paymentservice.entity.PaymentInfoEntity;
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
    public PaymentInfoEntity savePaymenttransaction(Paymentdto paymentdto){

        PaymentInfoEntity paymentInfo = new PaymentInfoEntity();
        paymentInfo.setPaymentMode(paymentdto.getPaymentMode());
        paymentInfo.setBookingId(paymentdto.getBookingId());
        paymentInfo.setUpiId(paymentdto.getUpiId());
        paymentInfo.setCardNumber(paymentdto.getCardNumber());
        return paymentdao.save(paymentInfo);
    }
}

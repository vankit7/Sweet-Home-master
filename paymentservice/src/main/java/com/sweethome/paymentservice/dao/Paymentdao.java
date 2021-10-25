package com.sweethome.paymentservice.dao;

import com.sweethome.paymentservice.entity.PaymentInfoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class Paymentdao {

    private SessionFactory sessionFactory;
    @Autowired
    public Paymentdao(EntityManagerFactory entityManagerFactory){
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

    }
    public PaymentInfoEntity save(PaymentInfoEntity paymentInfoEntity) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(paymentInfoEntity);
        transaction.commit();

        return paymentInfoEntity;
    }


}

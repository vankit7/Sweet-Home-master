package com.sweethome.Payment.dao;

import com.sweethome.Payment.entity.TransactionDetailsEntity;
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
    public TransactionDetailsEntity save(TransactionDetailsEntity transactionDetailsEntity) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(transactionDetailsEntity);
        transaction.commit();

        return transactionDetailsEntity;
    }

    public TransactionDetailsEntity findById(int transactionid) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TransactionDetailsEntity transactionDetailsEntity = session.get(TransactionDetailsEntity.class, transactionid);
        transaction.commit();
        session.close();
        return transactionDetailsEntity;
    }


}

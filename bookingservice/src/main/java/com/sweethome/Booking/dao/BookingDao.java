package com.sweethome.Booking.dao;

import com.sweethome.Booking.model.entity.BookingInfoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class BookingDao {

    private SessionFactory sessionFactory;

    @Autowired
    public BookingDao(EntityManagerFactory entityManagerFactory){
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

    }


    public BookingInfoEntity save(BookingInfoEntity bookingInfoEntity) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bookingInfoEntity);
        transaction.commit();

        return bookingInfoEntity;
    }

    public BookingInfoEntity findById(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        BookingInfoEntity bookingInfoEntity = session.get(BookingInfoEntity.class, id);
        transaction.commit();
        session.close();
        return bookingInfoEntity;
    }

    public BookingInfoEntity update(BookingInfoEntity bookingInfoEntity) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(bookingInfoEntity);
        transaction.commit();
        session.close();
        return bookingInfoEntity;
    }

}

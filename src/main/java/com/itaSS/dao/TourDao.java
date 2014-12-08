package com.itaSS.dao;

import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class TourDao extends BaseDao<Tour, Integer> {
    public TourDao(Class<Tour> type) {
        super(type);
    }

    public void setTourToHall(Hall hall, Tour tour) {
        Session session = null;
        try
        {
            session = SessionFact.getSessionFactory().openSession();
            session.getTransaction().begin();
            Hall hall1 = (Hall) session.get(Hall.class, hall.getId());
            Hall hall2 = (Hall) session.get(Hall.class, 6);
            Tour tor1 = (Tour) session.get(Tour.class, tour.getId());
            hall.getTour().add(tor1);
            tor1.getHall().add(hall1);
            tor1.getHall().add(hall2);
            session.save(hall1);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}

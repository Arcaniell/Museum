package com.itaSS.dao;

import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Set;


public class TourDao extends BaseDao<Tour, Integer> {
    public TourDao(Class<Tour> type) {
        super(type);
    }

    public void setHallsToTour(Set<Hall> halls, Tour idTour){
        Session session = null;
        try {
            session = SessionFact.getSessionFactory().openSession();
            Tour tour = (Tour) session.get(Tour.class, idTour.getId());
            session.getTransaction().begin();
            for (Hall hall : halls) {
                Hall buffHall = (Hall) session.get(Hall.class, hall.getId());
                tour.getHall().add(buffHall);
            }
            session.save(tour);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Set<Hall> getHallsFromTour(Tour idTour) {
        Session session = null;
        try {
            Tour tour = read(idTour.getId());
            session = SessionFact.getSessionFactory().openSession();
            session.getTransaction().begin();
//
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

}

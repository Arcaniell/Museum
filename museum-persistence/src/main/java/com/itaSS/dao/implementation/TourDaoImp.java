package com.itaSS.dao.implementation;

import com.itaSS.dao.TourDao;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class TourDaoImp extends BaseDaoImp<Tour, Integer>
        implements TourDao{
    public TourDaoImp() {
        super(Tour.class);
    }

    @Transactional
    public void setHallsToTour(Set<Hall> halls, int idTour){
        Session session = null;
        try {
            session = SessionFact.getSessionFactory().openSession();
            Tour tour = (Tour) session.get(Tour.class, idTour);
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

}

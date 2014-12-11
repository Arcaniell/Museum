package com.itaSS.dao;

import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;


public class TourDao extends BaseDao<Tour, Integer> {
    public TourDao(Class<Tour> type) {
        super(type);
    }

    public void setHallsToTour(Set<Hall> halls, Tour id_tour){
        Session session = null;
        try {
            session = SessionFact.getSessionFactory().openSession();
            Tour tour = (Tour) session.get(Tour.class, id_tour.getId());
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

    public List<Tour> getSpecTour(String input) {
        Session session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Tour.class);
        String[]args = input.split(" ");
        int argCounter = 0;
        if (!args[argCounter].equals("-")) {
            criteria.add(Restrictions.like("name", args[argCounter] + "%"));
        }
        //TODO REWORK!
        argCounter++;
        if (!args[argCounter].equals("-")) {
            criteria.add(Restrictions.like("beginDate", args[argCounter] + "%"));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            criteria.add(Restrictions.like("endDate", args[argCounter] + "%"));
        }
        List<Tour> results;
        results = criteria.list();
        return results;
    }

}

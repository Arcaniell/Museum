package com.itaSS.dao;

import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ExhibitDao extends BaseDao<Exhibit, Integer> {
    public ExhibitDao(Class<Exhibit> type) {
        super(type);
    }

    public void setExhibitToHall(Exhibit exhibit, Hall hall) {
        Session session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        exhibit.setHall(hall);
        update(exhibit);
        session.getTransaction().commit();
    }

    public List<Exhibit> getSpecExhibit(String input) {
        Session session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Exhibit.class);
        String[]args = input.split(" ");
        int argCounter = 0;
        if (!args[argCounter].equals("-")) {
            criteria.add(Restrictions.like("name", args[argCounter] + "%"));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            criteria.add(Restrictions.like("author_name", args[argCounter] + "%"));
        }
        List<Exhibit> results;
        results = criteria.list();
        return results;
    }
}

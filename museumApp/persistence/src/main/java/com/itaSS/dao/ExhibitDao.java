package com.itaSS.dao;

import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import org.hibernate.Session;

public class ExhibitDao extends BaseDao<Exhibit, Integer> {
    public ExhibitDao() {
        super(Exhibit.class);
    }

    public void setExhibitToHall(Exhibit exhibit, Hall hall) {
        Session session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        exhibit.setHall(hall);
        update(exhibit);
        session.getTransaction().commit();
    }
}

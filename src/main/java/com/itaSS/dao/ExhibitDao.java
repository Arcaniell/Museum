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

    public void addExhibitToHall(Exhibit id, Hall hall) {
        Session session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        Exhibit exhibit1 = (Exhibit) session.load(Exhibit.class, id.getId());
        exhibit1.setHall(hall);
        session.getTransaction().commit();
    }

    public Exhibit getSpecExhi(String name) {
        Session session = SessionFact.getSessionFactory().openSession();
        List<Exhibit> results;
        Criteria criteria = session.createCriteria(Exhibit.class)
                .add(Restrictions.like("name", name + "%"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        results = criteria.list();
        System.out.println(results);
        return null;
    }
}

package com.itaSS.dao;

import com.itaSS.entity.Hall;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class HallDao extends BaseDao<Hall, Integer> {
    public HallDao(Class<Hall> type) {
        super(type);
    }

    public List<Hall> getSpecHall(String input) {
        Session session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Hall.class);
        String[]args = input.split(" ");
        int argCounter = 0;
        if (!args[argCounter].equals("-")) {
            criteria.add(Restrictions.like("name", args[argCounter] + "%"));
        }
        List<Hall> results;
        results = criteria.list();
        return results;
    }

}

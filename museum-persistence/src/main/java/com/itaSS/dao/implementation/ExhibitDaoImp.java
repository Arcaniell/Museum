package com.itaSS.dao.implementation;

import com.itaSS.dao.ExhibitDao;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ExhibitDaoImp extends BaseDaoImp<Exhibit, Integer>
        implements ExhibitDao {
    public ExhibitDaoImp() {
        super(Exhibit.class);
    }

    @Transactional
    public void setExhibitToHall(Exhibit exhibit, Hall hall) {
        Session session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        exhibit.setHall(hall);
        update(exhibit);
        session.getTransaction().commit();
    }

    public Hall getHallFromExhibit(Exhibit idExhibit) {
        return read(idExhibit.getId()).getHall();
    }

}

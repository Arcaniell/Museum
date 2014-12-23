package com.itaSS.dao.implementation;

import com.itaSS.dao.WorkerDao;
import com.itaSS.entity.Job;
import com.itaSS.entity.Worker;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class WorkerDaoImp extends BaseDaoImp<Worker, Integer>
        implements WorkerDao{
    public WorkerDaoImp() {
        super(Worker.class);
    }

    @Transactional
    public void setWorkerToJob(int idWorker, int idJob) {
        Session session;
        try {
            session = SessionFact.getSessionFactory().openSession();
            session.getTransaction().begin();
            Worker worker = (Worker) session.get(Worker.class, idWorker);
            Job job = (Job) session.get(Job.class, idJob);
            worker.setJob(job);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Error adding Worker to Job");
            e.printStackTrace();
        }
    }

}

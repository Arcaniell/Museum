package com.itaSS.dao;

import com.itaSS.entity.Job;
import com.itaSS.entity.Worker;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class WorkerDao extends BaseDao<Worker, Integer> {
    public WorkerDao() {
        super(Worker.class);
    }

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

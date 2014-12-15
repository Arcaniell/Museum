package com.itaSS.dao;

import com.itaSS.entity.Job;
import com.itaSS.entity.Worker;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class WorkerDao extends BaseDao<Worker, Integer> {
    public WorkerDao() {
        super(Worker.class);
    }

    public void setWorkerToJob(Worker id_worker, Job id_job) {
        Session session;
        try {
            session = SessionFact.getSessionFactory().openSession();
            session.getTransaction().begin();
            Worker worker = (Worker) session.get(Worker.class, id_worker.getId());
            Job job = (Job) session.get(Job.class, id_job.getId());
            worker.setJob(job);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Error adding Worker to Job");
            e.printStackTrace();
        }
    }

}

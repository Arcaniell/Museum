package com.itaSS.dao;

import com.itaSS.entity.Job;
import com.itaSS.entity.Worker;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Entity;

public class WorkerDao<T extends Entity> extends BaseDao<Worker, Integer> {
    public WorkerDao(Class<Worker> type) {
        super(type);
    }

    public void setWorkerToJob(Worker id_worker, Job id_job) {
        Job job = new Job();
        Session session;
        try {
            session = SessionFact.getSessionFactory().openSession();
            session.getTransaction().begin();
            Worker worker = (Worker) session.get(Worker.class, id_worker.getId());
            Job hall = (Job) session.get(Job.class, id_job.getId());
            worker.setJob(job);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Error adding Worker to Hall");
            e.printStackTrace();
        }
    }

}

package com.itaSS.dao;

import com.itaSS.entity.Hall;
import com.itaSS.entity.Job;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Entity;

public class WorkerDao<T extends Entity> extends BaseDao<Worker, Integer> {
    public WorkerDao(Class<Worker> type) {
        super(type);
    }

    public void setWorkerToHall(Worker id_worker, Hall id_hall) {
        Job job = new Job();
        Session session;
        try {
            session = SessionFact.getSessionFactory().openSession();
            session.getTransaction().begin();
            Worker worker = (Worker) session.get(Worker.class, id_worker.getId());
            Hall hall = (Hall) session.get(Hall.class, id_hall.getId());
            job.setWorker(worker);
            new JobDao().create(job);
            worker.setJob(job);
            hall.setJob(job);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Error adding Worker to Hall");
            e.printStackTrace();
        }
    }

    public void setWorkerToTour(Worker id_worker, Tour id_tour) {
        Job job = new Job();
        Session session;
        try {
            session = SessionFact.getSessionFactory().openSession();
            session.getTransaction().begin();
            Worker worker = (Worker) session.get(Worker.class, id_worker.getId());
            Tour tour = (Tour) session.get(Tour.class, id_tour.getId());
            job.setWorker(worker);
            new JobDao().create(job);
            worker.setJob(job);
            tour.setJob(job);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Error adding Worker to Tour");
            e.printStackTrace();
        }
    }

}

package daoTests;

import com.itaSS.dao.*;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import static com.itaSS.utils.DataGenerators.*;
import org.hibernate.SessionFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class baseDaoTest {

    SessionFactory sessionFact;
    ExhibitDao exhibitDao = new ExhibitDao();
    HallDao hallDao = new HallDao();
    TourDao tourDao = new TourDao();
    WorkerDao workerDao = new WorkerDao();
    Exhibit exhibit;
    Hall hall;
    Tour tour;
    Worker worker;

    @Before
    public void initialization() {
        sessionFact = SessionFact.getSessionFactory();
        exhibit = genExhibit();
        hall = genHall();
        tour = genTour();
        worker = genWorker();
    }

    @Test
    public void createAndReadEntity() {
        exhibitDao.create(exhibit);
        hallDao.create(hall);
        tourDao.create(tour);
        workerDao.create(worker);

        Exhibit buffExhibit = exhibitDao.read(exhibit.getId());
        Hall buffHall = hallDao.read(hall.getId());
        Tour buffTour = tourDao.read(tour.getId());
        Worker buffWorker = workerDao.read(worker.getId());

        assertTrue(exhibit.equals(buffExhibit));
        assertEquals(hall, buffHall);
        assertEquals(tour, buffTour);
        assertEquals(worker, buffWorker);
    }

    public void updateEntity() {

    }

    public void deleteEntity() {

    }

    public void getSpecEntity() {

    }

    @After
    public void closeFactory() {
        if (sessionFact != null && sessionFact.isClosed()) {
            sessionFact.close();
        }
    }

}

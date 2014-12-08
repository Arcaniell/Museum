package com.itaSS;

import com.itaSS.dao.ExhibitDao;
import com.itaSS.dao.HallDao;
import com.itaSS.dao.SessionFact;
import com.itaSS.dao.TourDao;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.service.Console;
import com.itaSS.utils.DataGenerators;

public class App {

    public static void main(String[] args) {
        ExhibitDao exhibitDao = new ExhibitDao(Exhibit.class);
        HallDao hallDao = new HallDao(Hall.class);
        TourDao tourDao = new TourDao(Tour.class);
        for (Exhibit exhibit : DataGenerators.genExhibitList()) {
            exhibitDao.create(exhibit);
        }
        for (Hall hall : DataGenerators.genHallList()) {
            hallDao.create(hall);
        }
        for (Tour tour : DataGenerators.genTourList()) {
            tourDao.create(tour);
        }
        Exhibit exhibit = exhibitDao.read(1);
        Hall hall = hallDao.read(7);
        Tour tour = tourDao.read(8);
        exhibitDao.addExhibitToHall(exhibit, hall);
        tourDao.setTourToHall(hall, tour);
        //=====
        Console console = new Console();
        console.run();
        console.close();

    }
}

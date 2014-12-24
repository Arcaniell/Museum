package com.itaSS.service.implementation;

import com.itaSS.dao.ExhibitDao;
import com.itaSS.dao.HallDao;
import com.itaSS.dao.TourDao;
import com.itaSS.dao.WorkerDao;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Positions;
import com.itaSS.entity.enumInfo.Technics;
import com.itaSS.service.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Service
public final class DataGeneratorsImp implements DataGenerator{
    public static Random randomizer = new Random(43);
    private static Calendar calendar = Calendar.getInstance();
    private static final String[] EXHIBITS_NAME = {"Statuya1", "Statuya2", "Statuya3", "Statuya4",
            "Kartuna1", "Kartuna2", "Kartuna3", "Vaza1", "Vaza2", "Vaza3", "Vaza4"};
    private static final Set<String> MAPPED_EX_NAMES = new HashSet<>();
    private static final String[] AUTHORS_NAME = {"Vasiliy", "Sashko", "Bodia", "Tolik", "Pavlo",
            "Victoria", "Nazar"};
    private static final String[] HALLS_NAME = {"A", "B", "C", "D", "E", "F", "G"};
    private static final Set<String> MAPPED_HALL_NAMES = new HashSet<>();
    private static final String[] TOURS_NAME = {"Dino", "WW", "Hirosima", "Ukraine", "Poland", "Antlantida"};
    private static final String[] WORKERS_FIRST_NAME = {"Ania", "Petro", "Kolia", "Yan", "Arbuz", "Dinka", "Plant"};
    private static final String[] WORKERS_LAST_NAME = {"Abramovich", "Yanucovich", "Yaceniuk", "Poroshenko",
            "Samsung"};

    @Autowired
    private ExhibitDao exhibitDao;
    @Autowired
    private HallDao hallDao;
    @Autowired
    private TourDao tourDao;
    @Autowired
    private WorkerDao workerDao;

    static {
        for (String name : EXHIBITS_NAME) {
            MAPPED_EX_NAMES.add(name);
        }
        for (String name : HALLS_NAME) {
            MAPPED_HALL_NAMES.add(name);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static<T> T getRandVal(T[] arr) {
        T result;
        result = arr[randomizer.nextInt(arr.length)];
        return result;
    }

    private static Date genDate(int minYear, int maxYear) {
        final int nMounths = 12;
        final int nDays = 28;
        calendar.set(Calendar.YEAR, randomizer.nextInt(maxYear - minYear) + minYear);
        calendar.set(Calendar.MONTH, randomizer.nextInt(nMounths) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, randomizer.nextInt(nDays) + 1);
        return new Date(calendar.getTime().getTime());
    }

    public static Exhibit genExhibit() {
        Exhibit exhibit = new Exhibit();
        final int creationDateMin = 1500;
        final int creationDateMax = 1900;
        final int arrivalDateMin = 1980;
        final int arrivalDateMax = 2014;
        String exName = MAPPED_EX_NAMES.iterator().next();
        MAPPED_EX_NAMES.remove(exName);
        exhibit.setName(exName);
        exhibit.setArriveDate(genDate(arrivalDateMin, arrivalDateMax));
        exhibit.setCreationDate(genDate(creationDateMin, creationDateMax));
        exhibit.setAuthorName(AUTHORS_NAME[randomizer.nextInt(AUTHORS_NAME.length)]);
        exhibit.setMaterial(getRandVal(Materials.values()));
        exhibit.setTechnic(getRandVal(Technics.values()));
        return exhibit;
    }

    public static List<Exhibit> genExhibitList() {
        List<Exhibit> result = new ArrayList<>();
        for (int i = 0; i < EXHIBITS_NAME.length; i++) {
            result.add(genExhibit());
        }
        return result;
    }

    public static Hall genHall() {
        Hall hall = new Hall();
        String hallName = MAPPED_HALL_NAMES.iterator().next();
        hall.setName(hallName);
        MAPPED_HALL_NAMES.remove(hallName);
        return hall;
    }

    public static List<Hall> genHallList() {
        List<Hall> result = new ArrayList<>();
        for (int i = 0; i < HALLS_NAME.length; i++) {
            result.add(genHall());
        }
        return result;
    }

    public static Worker genWorker() {
        Worker worker = new Worker();
        worker.setFirstName(WORKERS_FIRST_NAME[randomizer.nextInt(WORKERS_FIRST_NAME.length)]);
        worker.setLastName(WORKERS_LAST_NAME[randomizer.nextInt(WORKERS_LAST_NAME.length)]);
        worker.setPosition(getRandVal(Positions.values()));
        worker.setSalary(new BigDecimal("10.50"));
        return worker;
    }

    public static List<Worker> genWorkersList() {
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < WORKERS_FIRST_NAME.length; i++) {
            workers.add(genWorker());
        }
        return workers;
    }

    public static Tour genTour() {
        final int beginDateMin = 2014;
        final int beginDateMax = 2016;
        Tour tour = new Tour();
        tour.setTourName(TOURS_NAME[randomizer.nextInt(TOURS_NAME.length)]);
        tour.setBeginDate(genDate(beginDateMin, beginDateMax));
        return tour;
    }

    public static List<Tour> genTourList() {
        List<Tour> list = new ArrayList<>();
        for (int i = 0; i < TOURS_NAME.length; i++) {
            list.add(genTour());
        }
        return list;
    }

    @Transactional
    public void generateData() {

        for (Exhibit exhibit : DataGeneratorsImp.genExhibitList()) {
            exhibitDao.create(exhibit);
        }
        for (Hall hall : DataGeneratorsImp.genHallList()) {
            hallDao.create(hall);
        }
        for (Tour tour : DataGeneratorsImp.genTourList()) {
            tourDao.create(tour);
        }
        for (Worker worker : DataGeneratorsImp.genWorkersList()) {
            workerDao.create(worker);
        }
    }

}

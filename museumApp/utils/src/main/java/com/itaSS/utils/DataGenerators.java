package com.itaSS.utils;

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

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

public final class DataGenerators {
    public static Random randomizer = new Random(43);
    private static final String[] EXHIBITS_NAME = {"Statuya1", "Statuya2", "Statuya3", "Statuya4",
            "Kartuna1", "Kartuna2", "Kartuna3", "Vaza1", "Vaza2", "Vaza3", "Vaza4"};
    private static final Set<String> MAPPED_EX_NAMES = new HashSet<>();
    private static int counter = 0;
    private static final String[] AUTHORS_NAME = {"Vasiliy", "Sashko", "Bodia", "Tolik", "Pavlo",
            "Victoria", "Nazar"};
    private static final String[] HALLS_NAME = {"A", "B", "C", "D", "E", "F", "G"};
    private static final Set<String> MAPPED_HALL_NAMES = new HashSet<>();
    private static final String[] TOURS_NAME = {"Dino", "WW", "Hirosima", "Ukraine", "Poland", "Antlantida"};
    private static final String[] WORKERS_FIRST_NAME = {"Ania", "Petro", "Kolia", "Yan", "Arbuz", "Dinka", "Plant"};
    private static final String[] WORKERS_LAST_NAME = {"Abramovich", "Yanucovich", "Yaceniuk", "Poroshenko",
            "Samsung"};
    static {
        for (String name : EXHIBITS_NAME) {
            MAPPED_EX_NAMES.add(name);
        }
        for (String name : HALLS_NAME) {
            MAPPED_HALL_NAMES.add(name);
        }
    }

    private static<T> T getRandVal(T[] arr) {
        T result;
        result = arr[randomizer.nextInt(arr.length)];
        return result;
    }

    public static Exhibit genExhibit() {
        Exhibit exhibit = new Exhibit();
        String exName = MAPPED_EX_NAMES.iterator().next();
        MAPPED_EX_NAMES.remove(exName);
        exhibit.setName(exName);
        Calendar calendar = Calendar.getInstance();
        calendar.set(randomizer.nextInt(25) + 1980, randomizer.nextInt(12) + 1, randomizer.nextInt(28) + 1);
        exhibit.setArriveDate(new Date(calendar.getTime().getTime()));
        calendar.set(randomizer.nextInt(500) + 1500, randomizer.nextInt(12) + 1, randomizer.nextInt(28) + 1);
        exhibit.setCreationDate(new Date(calendar.getTime().getTime()));
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
        worker.setSalary(new BigDecimal("10.5"));
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
        Tour tour = new Tour();
        tour.setTour_name(TOURS_NAME[randomizer.nextInt(TOURS_NAME.length)]);
        return tour;
    }

    public static void generateData() {
        ExhibitDao exhibitDao = new ExhibitDao();
        HallDao hallDao = new HallDao(Hall.class);
        TourDao tourDao = new TourDao(Tour.class);
        WorkerDao workerDao = new WorkerDao(Worker.class);
        for (Exhibit exhibit : DataGenerators.genExhibitList()) {
            exhibitDao.create(exhibit);
        }
        for (Hall hall : DataGenerators.genHallList()) {
            hallDao.create(hall);
        }
        for (Tour tour : DataGenerators.genTourList()) {
            tourDao.create(tour);
        }
        for (Worker worker : DataGenerators.genWorkersList()) {
            workerDao.create(worker);
        }
    }

    public static List<Tour> genTourList() {
        List<Tour> list = new ArrayList<>();
        for (int i = 0; i < TOURS_NAME.length; i++) {
            list.add(genTour());
        }
        return list;
    }
}

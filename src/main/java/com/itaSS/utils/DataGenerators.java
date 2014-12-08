package com.itaSS.utils;

import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Technics;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

public class DataGenerators {
    public static Random randomizer = new Random(43);
    private static final String[] EXHIBITS_NAME = {"Statuya1", "Statuya2", "Statuya3", "Statuya4"};
    private static final Set<String> MAPPED_EX_NAMES = new HashSet<>();
    private static Calendar calendar;
    private static int counter = 0;
    private static final String[] AUTHORS_NAME = {"Yurec", "Vasiliy,", "Sashko"};
    private static final String[] HALLS_NAME = {"A", "B", "C"};
    private static final Set<String> MAPPED_HALL_NAMES = new HashSet<>();
    private static final String[] TOURS_NAME = {"Dino", "WW", "Hirosima"};
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
        calendar = Calendar.getInstance();
        calendar.set(1993 + counter++, 1 + counter, 10 + counter);
        exhibit.setArrive_date(new Date(calendar.getTime().getTime()));
        exhibit.setAuthor_name(AUTHORS_NAME[randomizer.nextInt(AUTHORS_NAME.length)]);
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
        worker.setName("Petro");
        worker.setPosition("General");
        worker.setSalary(new BigDecimal("10.5"));
        return worker;
    }

    public static Tour genTour() {
        Tour tour = new Tour();
        tour.setName(TOURS_NAME[randomizer.nextInt(TOURS_NAME.length)]);
        return tour;
    }

    public static List<Tour> genTourList() {
        List<Tour> list = new ArrayList<>();
        for (int i = 0; i < TOURS_NAME.length; i++) {
            list.add(genTour());
        }
        return list;
    }
}

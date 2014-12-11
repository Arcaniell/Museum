package com.itaSS.service;

import com.itaSS.dao.TourDao;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import static com.itaSS.utils.ConsoleInputReader.readDate;
import static com.itaSS.utils.ConsoleInputReader.readLine;

public class TourService extends BaseService {

    public void addTour() {
        System.out.println("Please enter required info: ");
        Tour tour = new Tour();
        System.out.println("\tTour name");
        String name = readLine();
        tour.setName(name);
        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tBegin Date (YYYY-MM-DD)");
        Date infoDate = readDate();
        if (infoDate  != null) {
            tour.setBeginDate(infoDate);
        }
        System.out.println("\tEnding Date (YYYY-MM-DD)");
        infoDate = readDate();
        if (infoDate   != null) {
            tour.setEndDate(infoDate);
        }
        TourDao tourDao = new TourDao(Tour.class);
        tourDao.create(tour);
    }

    public Tour searchTour() {
        System.out.println("Enter criteria for Tour search in following format (\"-\" for skip): ");
        System.out.println("\ttour_name begin_date ending_date");
        TourDao tourDao = new TourDao(Tour.class);
        String input = readLine();
        List<Tour> tours = tourDao.getSpecTour(input);
        while (tours.size() > 1 || tours.size() == 0) {
            if (tours.size() == 0) {
                System.out.println("No results found!");
                break;
            }
            System.out.println("More the one result, enter search criteria again: ");
            System.out.println(tours);
            input = readLine();
            tours = tourDao.getSpecTour(input);
        }
        return tours.get(firstElement);
    }

    public void setHallsToTour(Set<Hall> halls, Tour id_tour) {
        TourDao tourDao = new TourDao(Tour.class);
        tourDao.setHallsToTour(halls, id_tour);
    }

}

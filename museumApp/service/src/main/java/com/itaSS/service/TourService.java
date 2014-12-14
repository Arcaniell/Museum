package com.itaSS.service;

import com.itaSS.dao.TourDao;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.service.utils.CriterionBuilder;
import org.hibernate.criterion.Criterion;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.itaSS.utils.ConsoleInputReader.readDate;
import static com.itaSS.utils.ConsoleInputReader.readLine;

public class TourService extends BaseService {

    private static TourDao tourDao = new TourDao(Tour.class);

    public void addTour() {
        Tour tour = enterTourInfo();
        tourDao.create(tour);
    }

    public void updateTour() {
        Tour oldTour = searchTour();
        tourDao.update(enterTourInfo(oldTour));
    }

    public void deleteTour() {
        System.out.println("ENTER INFO FOR TOUR TO DELETE");
        Tour tour = searchTour();
        tourDao.delete(tour);
    }

    private Tour enterTourInfo() {
        return enterTourInfo(null);
    }

    private Tour enterTourInfo(Tour tour) {
        System.out.println("Please enter required info: ");
        if (tour == null) {
            tour = new Tour();
        }
        System.out.println("\tTour name");
        String name = readLine();
        tour.setTour_name(name);

        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tBegin Date (YYYY-MM-DD)");
        Date infoDate = readDate();
        if (infoDate  != null) {
            tour.setBegin_Date(infoDate);
        }
        System.out.println("\tEnding Date (YYYY-MM-DD)");
        infoDate = readDate();
        if (infoDate   != null) {
            tour.setEnd_Date(infoDate);
        }
        return tour;
    }

    public Tour searchTour() {
        System.out.println("Enter criteria for Tour search in following format (\"-\" for skip): ");
        System.out.println("\ttour_name begin_date ending_date");
        String input = readLine();
        Set<Criterion> criteria = CriterionBuilder.getTourCriterion(input);
        Set<Tour> tours = tourDao.getSpecEntity(criteria);
        int result_size = tours.size();
        while (result_size == zero_result || result_size > many_results ) {
            if (result_size == zero_result) {
                System.out.println("No mathces found, try again!");
                System.out.println(searchOptions);
            } else {
                System.out.println(tours);
                System.out.println("More then single result, enter search criteria again: ");
                System.out.println(searchOptions);
            }
            input = readLine();
            criteria = CriterionBuilder.getTourCriterion(input);
            tours = tourDao.getSpecEntity(criteria);
            result_size = tours.size();
        }
        return (tours.size() == 0) ? null : tours.iterator().next();
    }

    public void setHallsToTour(Set<Hall> halls, Tour tour) {
        tourDao.setHallsToTour(halls, tour);
    }

    public void showHallsFromTour() {
        System.out.println("What tour are you looking for?");
        Tour tour = searchTour();
        Set<Hall> halls = tourDao.getHallsFromTour(tour);
        if (halls.size() == 0) {
            System.out.println("Tour is empty!");
        } else {
            for (Hall hall : halls) {
                System.out.println(hall);
            }
        }
    }
}

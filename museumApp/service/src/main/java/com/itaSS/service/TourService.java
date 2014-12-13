package com.itaSS.service;

import com.itaSS.dao.TourDao;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.service.utils.CriterionBuilder;
import org.hibernate.criterion.Criterion;

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

        TourDao tourDao = new TourDao(Tour.class);
        tourDao.create(tour);
    }

    public Tour searchTour() {
        TourDao tourDao = new TourDao(Tour.class);
        System.out.println("Enter criteria for Tour search in following format (\"-\" for skip): ");
        System.out.println("\ttour_name begin_date ending_date");
        String input = readLine();
        Set<Criterion> criteria = CriterionBuilder.getTourCriterion(input);
        List<Tour> tours = tourDao.getSpecEntity(criteria);
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
        return tours.get(firstElement);
    }

    public void setHallsToTour(Set<Hall> halls, Tour id_tour) {
        TourDao tourDao = new TourDao(Tour.class);
        tourDao.setHallsToTour(halls, id_tour);
    }

}

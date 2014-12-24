package com.itaSS.service.implementation;

import com.itaSS.dao.implementation.TourDaoImp;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.service.TourService;
import com.itaSS.service.utils.SearchOpt;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Set;

import static com.itaSS.service.utils.ConsoleInputReader.*;
import static com.itaSS.service.utils.RegExCheck.*;

@Service
public class TourServiceImp extends BaseServiceImp
        implements TourService{

    private static TourDaoImp tourDao = new TourDaoImp();

    public void addTour() {
        Tour tour = enterTourInfo();
        tourDao.create(tour);
    }

    public void updateTour() {
        System.out.println("ENTER INFO FOR TOUR TO UPDATE: ");
        Tour oldTour = searchTour();
        while (oldTour == null) {
            oldTour = searchTour();
        }
        System.out.println("ENTER NEW INFO: ");
        tourDao.update(enterTourInfo(oldTour));
    }

    public void deleteTour() {
        System.out.println("ENTER INFO FOR TOUR TO DELETE");
        Tour tour = searchTour();
        tourDao.remove(tour);
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
        if (!name.equals("")) {
            while (!checkForName(name)) {
                name = readLine();
            }
            tour.setTourName(name);
        }

        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tBegin Date (YYYY-MM-DD)");
        String readBeginDate = readLine();
        if (!readBeginDate.equals("")) {
            while (!checkForDate(readBeginDate)) {
                readBeginDate = readLine();
            }
            Date beginDate = readDate(readBeginDate);
            tour.setBeginDate(beginDate);
        }

        System.out.println("\tEnding Date (YYYY-MM-DD)");
        String readEndDate = readLine();
        if (!readEndDate.equals("")) {
            while (!checkForDate(readEndDate)) {
                readEndDate = readLine();
            }
            Date endDate = readDate(readEndDate);
            tour.setEndDate(endDate);
        }
        return tour;
    }

    public Tour searchTour() {
        System.out.println(searchOptions);
        Tour searchExample = enterTourInfo();
        Set<Criterion> criteria = SearchOpt.getTourCriterion(searchExample);
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
            searchExample = enterTourInfo();
            criteria = SearchOpt.getTourCriterion(searchExample);
            tours = tourDao.getSpecEntity(criteria);
            result_size = tours.size();
        }
        return (tours.size() == 0) ? null : tours.iterator().next();
    }

    public void setHallsToTour(Set<Hall> halls, Tour tour) {
        tourDao.setHallsToTour(halls, tour.getId());
    }

    public void showHallsFromTour() {
        System.out.println("What Tour are you looking for?");
        Tour tour = searchTour();
        Set<Hall> halls = tourDao.read(tour.getId()).getHall();
        if (halls.size() == 0) {
            System.out.println("Tour is empty!");
        } else {
            for (Hall hall : halls) {
                System.out.println(hall);
            }
        }
    }

    public void showExhibitsFromTour() {
        System.out.println("What Tour are you looking for?");
        Tour tour = searchTour();
        Set<Hall> halls = tourDao.read(tour.getId()).getHall();
        if (halls.size() == 0) {
            System.out.println("Tour is empty!");
        } else {
            for (Hall hall : halls) {
                for(Exhibit exhibit : hall.getExhibits()) {
                    System.out.println(exhibit);
                }
            }
        }
    }

}

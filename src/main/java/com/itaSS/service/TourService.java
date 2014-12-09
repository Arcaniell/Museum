package com.itaSS.service;

import com.itaSS.dao.TourDao;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;

import java.util.List;
import java.util.Set;

import static com.itaSS.utils.ConsoleInputReader.readLine;

public class TourService extends BaseService {

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
        tourDao.setHallsToTout(halls, id_tour);
    }

}

package com.itaSS.service;

import com.itaSS.dao.HallDao;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.service.utils.CriterionBuilder;
import org.hibernate.criterion.Criterion;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.itaSS.utils.ConsoleInputReader.readLine;

public class HallService extends BaseService{

    public void addHall() {
        System.out.println("Please enter required info: ");
        Hall hall = new Hall();
        System.out.println("\tHall name");
        String hallName = readLine();
        hall.setName(hallName);
        HallDao hallDao = new HallDao(Hall.class);
        hallDao.create(hall);
    }

    public Hall searchHall() {
        System.out.println("Enter criteria for Hall search in following format (\"-\" for skip): ");
        System.out.println("\thall_name");
        HallDao hallDao = new HallDao(Hall.class);
        String input = readLine();
        Set<Criterion> criteria = CriterionBuilder.getHallCriterion(input);
        List<Hall> halls = hallDao.getSpecEntity(criteria);
        while (halls.size() > 1 || halls.size() == 0) {
            if (halls.size() == 0) {
                System.out.println("No results found!");
                break;
            }
            System.out.println("More the one result, enter search criteria again: ");
            System.out.println(halls);
            input = readLine();
            criteria = CriterionBuilder.getExhibitCriterion(input);
            halls = hallDao.getSpecEntity(criteria);
        }
        return halls.get(firstElement);
    }

    public void setHallsToTour() {
        Set<Hall> halls = new HashSet<>();
        String input = "";
        boolean firstRun = true;
        while (true) {
            if (!firstRun) {
                System.out.println("Select another Hall or enter exit");
                input = readLine();
                if (input.equals("exit")) {
                    break;
                }
            }
            halls.add(searchHall());
            firstRun = false;
        }
        System.out.println(halls);

        TourService tourService = new TourService();
        Tour tour = tourService.searchTour();
        System.out.println(tour);

        tourService.setHallsToTour(halls, tour);
    }

}

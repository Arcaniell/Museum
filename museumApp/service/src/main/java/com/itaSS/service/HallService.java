package com.itaSS.service;

import com.itaSS.dao.HallDao;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.service.utils.CriterionBuilder;
import org.hibernate.criterion.Criterion;

import java.util.HashSet;
import java.util.Set;

import static com.itaSS.utils.ConsoleInputReader.readLine;

public class HallService extends BaseService{

    private static HallDao hallDao = new HallDao();

    public void addHall() {
        Hall hall = enterHallInfo();
        hallDao.create(hall);
    }

    public void updateHall() {
        System.out.println("ENTER INFO FOR HALL TO UPDATE: ");
        Hall hallOld = searchHall();
        while (hallOld == null) {
            hallOld = searchHall();
        }
        System.out.println("ENTER NEW INFO: ");
        hallDao.update(enterHallInfo(hallOld));
    }

    public void deleteHall() {
        System.out.println("ENTER INFO FOR HALL TO DELETE");
        Hall hall = searchHall();
        hallDao.delete(hall);
    }

    public Hall searchHall() {
        return searchHall(null);
    }

    public Hall searchHall(String input) {
        System.out.println(searchOptions);
        Hall searchExample = enterHallInfo();
        Set<Criterion> criteria = CriterionBuilder.getHallCriterion(searchExample);
        Set<Hall> halls = hallDao.getSpecEntity(criteria);
        int result_size = halls.size();
        while (result_size > 1 || result_size == 0) {
            if (result_size == 0) {
                System.out.println("No results found!");
                break;
            }
            System.out.println("More the one result, enter search criteria again: ");
            System.out.println(halls);
            if (input == null) {
                input = readLine();
            }
            searchExample = enterHallInfo();
            criteria = CriterionBuilder.getHallCriterion(searchExample);
            halls = hallDao.getSpecEntity(criteria);
            result_size = halls.size();
        }
        return (halls.size() == zero_result) ? null : halls.iterator().next();
    }

    public void setHallsToTour() {
        Set<Hall> halls = new HashSet<>();
        while (true) {
            System.out.println("Select Hall or enter exit");
            System.out.println(searchOptions);
            String input = readLine();
            if (input.equals("exit")) {
                break;
            } else {
                halls.add(searchHall(input));
            }
        }
        System.out.println(halls);

        TourService tourService = new TourService();
        Tour tour = tourService.searchTour();
        System.out.println(tour);

        tourService.setHallsToTour(halls, tour);
    }

    public Hall enterHallInfo() {
        return enterHallInfo(null);
    }

    public Hall enterHallInfo(Hall id_hall) {
        System.out.println("Please enter required info: ");
        if (id_hall == null) {
            id_hall = new Hall();
        }
        System.out.println("\tHall name");
        String hallName = readLine();
        id_hall.setName(hallName);
        return id_hall;
    }

}

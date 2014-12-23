package com.itaSS.service.implementation;

import com.itaSS.dao.implementation.HallDaoImp;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.service.HallService;
import com.itaSS.service.utils.SearchOpt;

import javax.persistence.criteria.CriteriaQuery;
import java.util.HashSet;
import java.util.Set;

import static com.itaSS.service.utils.ConsoleInputReader.readLine;
import static com.itaSS.service.utils.RegExCheck.*;

public class HallServiceImp extends BaseServiceImp
        implements HallService{

    private static HallDaoImp hallDao = new HallDaoImp();

    public void addHall() {
        Hall hall = createHall();
        hallDao.create(hall);
    }

    public void updateHall() {
        System.out.println("ENTER INFO FOR HALL TO UPDATE: ");
        Hall hallOld = searchHall();
        while (hallOld == null) {
            hallOld = searchHall();
        }
        System.out.println("ENTER NEW INFO: ");
        //TODO Make this
        hallDao.update(enterHallInfo(hallOld));
    }

    public void deleteHall() {
        System.out.println("ENTER INFO FOR HALL TO DELETE");
        Hall hall = searchHall();
        hallDao.remove(hall);
    }

    public Hall searchHall() {
        System.out.println(searchOptions);
        String line = readLine();
        CriteriaQuery<Hall> cq = SearchOpt.getCQ(Hall.class, line);
        Set<Hall> halls = hallDao.getSpecEntity(cq);
        int result_size = halls.size();
        while (result_size > 1 || result_size == 0) {
            if (result_size == 0) {
                System.out.println("No results found!");
                break;
            }
            System.out.println("More the one result, enter search criteria again: ");
            System.out.println(halls);
            if (line == null) {
                line = readLine();
            }
            result_size = halls.size();
        }
        return (halls.size() == zero_result) ? null : halls.iterator().next();
    }

    public void setHallsToTour() {
        Set<Hall> halls = new HashSet<>();
        halls.add(searchHall());
        while (true) {
            System.out.println("Select more Halls? (y/n)");
            String input = readLine();
            if (input.equals("n")) {
                break;
            } else if (input.equals("y")) {
                halls.add(searchHall());
            } else {
                System.out.println("WHAT????");
            }
        }
        System.out.println(halls);

        TourServiceImp tourService = new TourServiceImp();
        Tour tour = tourService.searchTour();
        System.out.println(tour);

        tourService.setHallsToTour(halls, tour);
    }

    public void showToursForHall() {
        Hall hall = searchHall();
        for (Tour tour : hall.getTour()) {
            System.out.println(tour);
        }
    }

    private String enterHallInfo() {
        System.out.println("Please enter required info: ");
        System.out.println("\tHall name");
        String hallName = readLine();
        while (!checkForName(hallName)) {
            hallName = readLine();
        }
        return hallName;
    }

    private Hall enterHallInfo(Hall hall) {
        System.out.println("Please enter required info: ");
        System.out.println("\tHall name");
        String hallName = readLine();
        while (!checkForName(hallName)) {
            hallName = readLine();
            hall.setName(hallName);
        }
        return hall;
    }

    private Hall createHall() {
        Hall hall = new Hall();
        hall.setName(enterHallInfo());
        return hall;
    }

}

package com.itaSS.interact;

import com.itaSS.dao.*;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Positions;
import com.itaSS.entity.enumInfo.Technics;
import com.itaSS.service.ExhibitService;
import com.itaSS.service.HallService;
import com.itaSS.service.TourService;
import com.itaSS.utils.ConsoleInputReader;

import static com.itaSS.utils.ConsoleInputReader.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Console {

    public void run() {
        //TODO All data in Enum
//        final int ACTION_NUM = 10;
//        final int ADD_EXHIBIT = 1;
//        final int ADD_HALL = 2;
//        final int ADD_TOUR = 3;
//        final int ADD_WORKER = 4;
//        final int SET_EXHIBIT_TO_HALL = 5;
//        final int SET_HALL_TO_TOUR = 6;
//        final int SET_WORKER_TO_HALL = 7;
//        final int SET_WORKER_TO_TOUR = 8;
//        final int SET_DATES_TO_TOUR = 9;
//        final int SHOW_HALLS_AND_EXHIBITS_IN_TOUR = 10;
//
//        showAction();
//        int selected = 0;
//        String input = readLine();
//        if (checkMenuSelect(input)) {
//             selected = Integer.valueOf(input);
//        }
//        else {
//            System.out.println("Wrong format, try again!");
//        }
        Enum<Actions> selected = (Actions) selectEnum(Actions.class);

        if (selected == Actions.ADD_EXHIBIT) {
            addExhibit();
        } else if (selected == Actions.ADD_HALL) {
            addHall();
        } else if (selected == Actions.ADD_TOUR) {
            addTour();
        } else if (selected == Actions.ADD_WORKER) {
            addWorker();
        } else if (selected == Actions.SET_EXHIBIT_TO_HALL) {
            setExhibitToHall();
        } else if (selected == Actions.SET_HALL_TO_TOUR) {
            setHallsToTour();
        } else if (selected == Actions.SET_WORKER_TO_HALL) {
            setWorkerToHall();
        }
    }
    //TODO Do all that stuff by services

    private void addExhibit() {
        System.out.println("Please enter required info: ");
        Exhibit exhibit = new Exhibit();
        //TODO Check formats for names
        System.out.println("\tAuthor Name: ");
        String authorName = readLine();
        exhibit.setAuthor_name(authorName);

        System.out.println("\tExhibit Name: ");
        String exhibitName = readLine();
        exhibit.setName(exhibitName);

        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tArrival Date (YYYY-MM-DD)");
        Date arrivalDate = readDate();
        if (arrivalDate != null) {
            exhibit.setArrive_date(arrivalDate);
        }
        System.out.println("\tMaterial: ");
        Materials material = (Materials) selectEnum(Materials.class);
        if (material != null) {
            exhibit.setMaterial(material);
        }
        System.out.println("\tTechnic: ");
        Technics technics = (Technics) selectEnum(Technics.class);
        if (technics != null) {
            exhibit.setTechnic(technics);
        }
        ExhibitDao exhibitDao = new ExhibitDao(Exhibit.class);
        exhibitDao.create(exhibit);
    }

    private void addHall() {
        System.out.println("Please enter required info: ");
        Hall hall = new Hall();
        System.out.println("\tHall name");
        String hallName = readLine();
        hall.setName(hallName);
        HallDao hallDao = new HallDao(Hall.class);
        hallDao.create(hall);
    }

    private void addTour() {
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

    private void addWorker() {
        System.out.println("Please enter required info: ");
        Worker worker = new Worker();
        System.out.println("\tWorker name");
        String name = readLine();
        worker.setName(name);
        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tWorker salary: ");
        String input = readLine();
        //TODO Salary format check
        if (!input.equals("")) {
            worker.setSalary(new BigDecimal(input));
        }
        System.out.println("\tPosition: ");
        Positions position = (Positions) selectEnum(Positions.class);
        if (position != null) {
            worker.setPosition(position);
        }
        WorkerDao workerDao = new WorkerDao(Worker.class);
        workerDao.create(worker);
    }

    private void setExhibitToHall() {
        ExhibitService exhibitService = new ExhibitService();
        Exhibit exhibit = exhibitService.searchExhibit();

        HallService hallService = new HallService();
        Hall hall = hallService.searchHall();

        ExhibitDao exhibitDao = new ExhibitDao(Exhibit.class);
        exhibitDao.setExhibitToHall(exhibit, hall);
    }

    private void setHallsToTour() {
        HallService hallService = new HallService();
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
            halls.add(hallService.searchHall());
            firstRun = false;
        }
        System.out.println(halls);

        TourService tourService = new TourService();
        Tour tour = tourService.searchTour();
        System.out.println(tour);

        tourService.setHallsToTour(halls, tour);
    }

    private void setWorkerToHall() {

    }

    private Date readDate() {
        Date result;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            String input = readLine();
            if (!input.equals("")) {
                result = new Date(dateFormat.parse(input).getTime());
                return result;
            }
        } catch (ParseException e) {
            System.out.println("Parse err");
            e.printStackTrace();
        }
        return null;
    }

    private Enum<?> selectEnum(Class<? extends Enum<?>> type) {
        int counter = 1;
        for (Enum<?> val : type.getEnumConstants()) {
            System.out.print("# " + (counter++) + val.toString() + " ");
        }
        System.out.println();
        Enum<?> material;
        String input = readLine();
        if (!input.equals("") && checkMenuSelect(input)) {
            int selected = Integer.valueOf(input) - 1;
            material = Arrays.asList(type.getEnumConstants()).get(selected);
            return material;
        }
        return null;
    }

    private boolean checkMenuSelect(String string) {
        return string.matches("[1-9][0-9]*");
    }

    public void close() {
        try {
            SessionFact.closeFactory();
            ConsoleInputReader.close();
        } catch (IOException e) {
            System.out.println("Error while closing Console Resources!!!");
            e.printStackTrace();
        }
    }

}

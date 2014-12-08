package com.itaSS.service;

import com.itaSS.dao.*;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Technics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Console {

    private BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        final int ACTION_NUM = 5;
        final int ADD_EXHIBIT = 1;
        final int ADD_HALL = 2;
        final int ADD_TOUR = 3;
        final int ADD_WORKER = 4;
        final int ADD_EXHIBIT_TO_HALL = 5;

        showAction();
        int selected = 0;
        try {
            String input = inConsole.readLine();
            if (checkMenuSelect(input)) {
                 selected = Integer.valueOf(input);
            }
            else {
                System.out.println("Wrong format, try again!");
            }
        } catch (IOException e) {
            System.out.println();
        }

        if (selected > ACTION_NUM) {
            System.out.println("No such action!");
        } else if (selected == ADD_EXHIBIT) {
            addExhibit();
        } else if (selected == ADD_HALL) {
            addHall();
        } else if (selected == ADD_TOUR) {
            addTour();
        } else if (selected == ADD_WORKER) {
            addWorker();
        } else if (selected == ADD_EXHIBIT_TO_HALL) {
            addExhibitToHall();
        }
    }

    private void showAction() {
        System.out.println("Hi! What you want to do?");
        System.out.println("1. Add Exhibit");
        System.out.println("2. Add Hall");
        System.out.println("3. Add Tour");
        System.out.println("4. Add Worker");
    }

    private void addExhibit() {
        System.out.println("Please enter required info: ");
        Exhibit exhibit = new Exhibit();
        try {
            //TODO Check formats for names
            System.out.println("\tAuthor Name: ");
            String authorName = inConsole.readLine();
            exhibit.setAuthor_name(authorName);
            System.out.println("\tExhibit Name: ");
            String exhibitName = inConsole.readLine();
            exhibit.setName(exhibitName);
        } catch (IOException e) {
            System.out.println("Wrong input!");
            e.printStackTrace();
        }
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
        try {
            String hallName = inConsole.readLine();
            hall.setName(hallName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HallDao hallDao = new HallDao(Hall.class);
        hallDao.create(hall);
    }

    private void addTour() {
        System.out.println("Please enter required info: ");
        Tour tour = new Tour();
        System.out.println("\tTour name");
        try {
            String name = inConsole.readLine();
            tour.setName(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tBegin Date (YYYY-MM-DD)");
        Date infoDate = readDate();
        if (infoDate  != null) {
            tour.setBeginDate(infoDate);
        }
        System.out.println("\tEnding Date (YYYY-MM-DD)");
        infoDate = readDate();
        if (infoDate   != null) {
            tour.setBeginDate(infoDate);
        }
        TourDao tourDao = new TourDao(Tour.class);
        tourDao.create(tour);
    }

    private void addWorker() {
        System.out.println("Please enter required info: ");
        Worker worker = new Worker();
        try {
            System.out.println("\tWorker name");
            String name = inConsole.readLine();
            worker.setName(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tWorker salary: ");
        try {
            String input = inConsole.readLine();
            //TODO Salary format check
            if (!input.equals("")) {
                worker.setSalary(new BigDecimal(input));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\tWorker position: ");
        try {
            String position = inConsole.readLine();
            if (!position.equals("")) {
                //TODO Better ENUM
                worker.setPosition(position);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        WorkerDao workerDao = new WorkerDao();
        workerDao.create(worker);
    }

    private void addExhibitToHall() {
        //TODO Implement this method
    }

    private Date readDate() {
        Date result;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            String input = inConsole.readLine();
            if (!input.equals("")) {
                result = new Date(dateFormat.parse(input).getTime());
                return result;
            }
        } catch (ParseException e) {
            System.out.println("Parse err");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO err");
            e.printStackTrace();
        }
        return null;
    }

    private Enum<?> selectEnum(Class<? extends Enum<?>> type) {
        int counter = 1;
        for (Enum<?> val : type.getEnumConstants()) {
            System.out.print("#" + (counter++) + val.toString() + " ");
        }
        System.out.println();
        try {
            Enum<?> material;
            String input = inConsole.readLine();
            if (!input.equals("") && checkMenuSelect(input)) {
                int selected = Integer.valueOf(input) - 1;
                material = Arrays.asList(type.getEnumConstants()).get(selected);
                return material;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkMenuSelect(String string) {
        return string.matches("[1-9][0-9]*");
    }

    public void close() {
        try {
            SessionFact.closeFactory();
            inConsole.close();
        } catch (IOException e) {
            System.out.println("Error while closing Console Resources!!!");
            e.printStackTrace();
        }
    }

}

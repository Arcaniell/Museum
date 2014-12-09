package com.itaSS.service;

import com.itaSS.dao.HallDao;
import com.itaSS.entity.Hall;

import java.util.List;

import static com.itaSS.utils.ConsoleInputReader.readLine;

public class HallService extends BaseService{

    public Hall searchHall() {
        System.out.println("Enter criteria for Hall search in following format (\"-\" for skip): ");
        System.out.println("\thall_name");
        HallDao hallDao = new HallDao(Hall.class);
        String input = readLine();
        List<Hall> halls = hallDao.getSpecHall(input);
        while (halls.size() > 1 || halls.size() == 0) {
            if (halls.size() == 0) {
                System.out.println("No results found!");
                break;
            }
            System.out.println("More the one result, enter search criteria again: ");
            System.out.println(halls);
            input = readLine();
            halls = hallDao.getSpecHall(input);
        }
        return halls.get(firstElement);
    }
}

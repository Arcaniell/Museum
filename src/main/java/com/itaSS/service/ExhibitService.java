package com.itaSS.service;

import com.itaSS.dao.ExhibitDao;
import com.itaSS.entity.Exhibit;

import java.util.List;

import static com.itaSS.utils.ConsoleInputReader.readLine;

public class ExhibitService extends BaseService {

    public Exhibit searchExhibit() {
        System.out.println("Enter criteria for Exhibit search in following format (\"-\" for skip): ");
        System.out.println("\texhibit_name author_name arrive_date material technic");
        ExhibitDao exhibitDao = new ExhibitDao(Exhibit.class);
        String input = readLine();
        List<Exhibit> exhibits = exhibitDao.getSpecExhibit(input);
        while (exhibits.size() > 1 || exhibits.size() == 0) {
            if (exhibits.size() == 0) {
                System.out.println("No results found!");
                break;
            }
            System.out.println("More the one result, enter search criteria again: ");
            System.out.println(exhibits);
            input = readLine();
            exhibits = exhibitDao.getSpecExhibit(input);
        }
        return exhibits.get(firstElement);
    }
}

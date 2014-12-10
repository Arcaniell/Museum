package com.itaSS.service;

import com.itaSS.dao.ExhibitDao;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Technics;

import java.sql.Date;
import java.util.List;

import static com.itaSS.utils.ConsoleInputReader.*;

public class ExhibitService extends BaseService {

    public void addExhibit() {
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
        System.out.println("\tCreation Date (YYYY-MM-DD)");
        Date creationDate = readDate();
        if (arrivalDate != null) {
            exhibit.setCreation_date(creationDate);
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

    public Exhibit searchExhibit() {
        System.out.println("Enter criteria for Exhibit search in following format (\"-\" for skip): ");
        System.out.println("\texhibit_name author_name arrive_date creation_date material technic");
        ExhibitDao exhibitDao = new ExhibitDao(Exhibit.class);
        String input = readLine();
        List<Exhibit> exhibits = exhibitDao.getSpecExhibit(input);
        while (exhibits.size() > 1 || exhibits.size() == 0) {
            if (exhibits.size() == 0) {
                System.out.println("No results found!");
                break;
            }
            System.out.println(exhibits);
            System.out.println("More the one result, enter search criteria again: ");
            input = readLine();
            exhibits = exhibitDao.getSpecExhibit(input);
        }
        return exhibits.get(firstElement);
    }

    public void setExhibitToHall(Exhibit exhibit, Hall hall) {
        ExhibitDao exhibitDao = new ExhibitDao(Exhibit.class);
        exhibitDao.setExhibitToHall(exhibit, hall);
    }
}

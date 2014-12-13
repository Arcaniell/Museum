package com.itaSS.service;

import com.itaSS.dao.ExhibitDao;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Technics;
import com.itaSS.service.utils.CriterionBuilder;
import org.hibernate.criterion.Criterion;

import java.sql.Date;
import java.util.List;
import java.util.Set;

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

        System.out.println("\tCreation Date (YYYY-MM-DD)");
        Date creationDate = readDate();
        if (creationDate != null) {
            exhibit.setCreation_date(creationDate);
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

        ExhibitDao exhibitDao = new ExhibitDao();
        exhibitDao.create(exhibit);
    }

    public Exhibit searchExhibit() {
        System.out.println(searchOptions);
        System.out.println("\texhibit_name author_name creation_date arrive_date material technic");
        ExhibitDao exhibitDao = new ExhibitDao();
        String input = readLine();
        Set<Criterion> criteria = CriterionBuilder.getExhibitCriterion(input);
        List<Exhibit> exhibits = exhibitDao.getSpecEntity(criteria);
        int result_size = exhibits.size();
        while (result_size == zero_result || result_size > many_results ) {
            if (result_size == zero_result) {
                System.out.println("No mathces found, try again!");
                System.out.println(searchOptions);
            } else {
                System.out.println(exhibits);
                System.out.println("More then single result, enter search criteria again: ");
                System.out.println(searchOptions);
            }
            input = readLine();
            criteria = CriterionBuilder.getExhibitCriterion(input);
            exhibits = exhibitDao.getSpecEntity(criteria);
            result_size = exhibits.size();
        }
        return exhibits.get(firstElement);
    }

    public void setExhibitToHall(Exhibit exhibit, Hall hall) {
        ExhibitDao exhibitDao = new ExhibitDao();
        exhibitDao.setExhibitToHall(exhibit, hall);
    }
}

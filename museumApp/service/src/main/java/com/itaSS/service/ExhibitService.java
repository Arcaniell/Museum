package com.itaSS.service;

import com.itaSS.dao.ExhibitDao;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Technics;
import com.itaSS.service.utils.CriterionBuilder;
import org.hibernate.criterion.Criterion;

import java.sql.Date;
import java.util.Set;

import static com.itaSS.utils.ConsoleInputReader.*;

public class ExhibitService extends BaseService {

    private static ExhibitDao exhibitDao = new ExhibitDao();

    public void addExhibit() {
        Exhibit exhibit = enterExhibitInfo();
        exhibitDao.create(exhibit);
    }

    public void updateExhibit() {
        System.out.println("ENTER INFO FOR EXHIBIT TO UPDATE: ");
        Exhibit exhibitOld = searchExhibit();
        System.out.println("ENTER NEW INFO: ");
        exhibitDao.update(enterExhibitInfo(exhibitOld));
    }

    public void deleteExhibit() {
        System.out.println("ENTER INFO FOR EXHIBIT TO DELETE");
        Exhibit exhibit = searchExhibit();
        exhibitDao.delete(exhibit);
    }

    public Exhibit searchExhibit() {
        System.out.println(searchOptions);
        Exhibit searchExample = enterExhibitInfo();
        Set<Criterion> criteria = CriterionBuilder.getExhibitCriterion(searchExample);
        Set<Exhibit> exhibits = exhibitDao.getSpecEntity(criteria);
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
            searchExample = enterExhibitInfo();
            criteria = CriterionBuilder.getExhibitCriterion(searchExample);
            exhibits = exhibitDao.getSpecEntity(criteria);
            result_size = exhibits.size();
        }
        return (exhibits.size() == 0) ? null : exhibits.iterator().next();
    }

    public void setExhibitToHall(Exhibit exhibit, Hall hall) {
        ExhibitDao exhibitDao = new ExhibitDao();
        exhibitDao.setExhibitToHall(exhibit, hall);
    }

    public void showToursForExhibit(){
        System.out.println("What tour are you looking for?");
      for (Tour tour : searchExhibit().getHall().getTour()) {
                System.out.println(tour);
            }
        }

    private Exhibit enterExhibitInfo() {
        return enterExhibitInfo(null);
    }

    private Exhibit enterExhibitInfo(Exhibit exhibit) {
        System.out.println("Please enter required info: ");
        if (exhibit == null) {
            exhibit = new Exhibit();
        }

        System.out.println("\tAuthor Name: ");
        String authorName = readLine();
        if (!authorName.equals("")) {
            while (!checkName(authorName)) {
                authorName = readLine();
            }
            exhibit.setAuthorName(authorName);
        }

        System.out.println("\tExhibit Name: ");
        String exhibitName = readLine();
        if (!exhibitName.equals("")) {
            while (!checkComplexName(exhibitName)) {
                exhibitName = readLine();
            }
            exhibit.setName(exhibitName);
        }

        System.out.println("\tCreation Date (YYYY-MM-DD)");
        String readCreationDate = readLine();
        if (!readCreationDate.equals("")) {
            while (!checkDate(readCreationDate)) {
                readCreationDate = readLine();
            }
            Date creationDate = readDate(readCreationDate);
            exhibit.setCreationDate(creationDate);
        }

        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tArrival Date (YYYY-MM-DD)");
        String readArrivalDate = readLine();
        if (!readArrivalDate.equals("")) {
            while (!checkDate(readArrivalDate)) {
                readArrivalDate = readLine();
            }
            Date arrivalDate = readDate(readArrivalDate);
            exhibit.setArriveDate(arrivalDate);
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
        return exhibit;
    }
}

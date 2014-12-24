package com.itaSS.service.implementation;

import com.itaSS.dao.implementation.ExhibitDaoImp;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Technics;
import com.itaSS.service.ExhibitService;
import com.itaSS.service.HallService;
import com.itaSS.service.utils.SearchOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import static com.itaSS.service.utils.ConsoleInputReader.*;
import static com.itaSS.service.utils.RegExCheck.*;

@Service
public class ExhibitServiceImp extends BaseServiceImp
        implements ExhibitService{

    private static ExhibitDaoImp exhibitDao = new ExhibitDaoImp();

    @Autowired
    HallService hallService;

    public List<Exhibit> getAll() {
        return exhibitDao.getAll();
    }

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
        exhibitDao.remove(exhibit);
    }

    public Exhibit searchExhibit() {
        System.out.println(searchOptions);
        Exhibit searchExample = enterExhibitInfo();
        CriteriaQuery<Exhibit> cq = SearchOpt.getCQ(Exhibit.class, parseExhibit(searchExample));
//        Set<Criterion> criteria = SearchOpt.getExhibitCriterion(searchExample);
        Set<Exhibit> exhibits = exhibitDao.getSpecEntity(cq);
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
//            searchExample = enterExhibitInfo();
//            criteria = SearchOpt.getExhibitCriterion(searchExample);
//            exhibits = exhibitDao.getSpecEntity(criteria);
            result_size = exhibits.size();
        }
        return (exhibits.size() == 0) ? null : exhibits.iterator().next();
    }

    public void setExhibitToHall(Exhibit exhibit, Hall hall) {
        ExhibitDaoImp exhibitDao = new ExhibitDaoImp();
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
            while (!checkForName(authorName)) {
                authorName = readLine();
            }
            exhibit.setAuthorName(authorName);
        }

        System.out.println("\tExhibit Name: ");
        String exhibitName = readLine();
        if (!exhibitName.equals("")) {
            while (!checkForComplexName(exhibitName)) {
                exhibitName = readLine();
            }
            exhibit.setName(exhibitName);
        }

        System.out.println("\tCreation Date (YYYY-MM-DD)");
        String readCreationDate = readLine();
        if (!readCreationDate.equals("")) {
            while (!checkForDate(readCreationDate)) {
                readCreationDate = readLine();
            }
            Date creationDate = readDate(readCreationDate);
            exhibit.setCreationDate(creationDate);
        }

        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tArrival Date (YYYY-MM-DD)");
        String readArrivalDate = readLine();
        if (!readArrivalDate.equals("")) {
            while (!checkForDate(readArrivalDate)) {
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

    private String parseExhibit(Exhibit exhibit) {
        String result = "";
        if (exhibit.getName() != null) {
            result += exhibit.getName() + " ";
        } else {
            result += "- ";
        }
        if (exhibit.getAuthorName() != null) {
            result += exhibit.getAuthorName() + " ";
        } else {
            result += "- ";
        }
        if (exhibit.getArriveDate() != null) {
            result += exhibit.getCreationDate().toString() + " ";
        } else {
            result += "- ";
        }
        if (exhibit.getArriveDate() != null) {
            result += exhibit.getArriveDate().toString() + " ";
        } else {
            result += "- ";
        }
        if (exhibit.getMaterial() != null) {
            result += exhibit.getMaterial().toString() + " ";
        } else {
            result += "- ";
        }
        if (exhibit.getTechnic() != null) {
            result += exhibit.getTechnic().toString() + " ";
        } else {
            result += "- ";
        }
        return result;
    }
}

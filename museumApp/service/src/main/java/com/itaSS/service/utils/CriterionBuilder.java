package com.itaSS.service.utils;

import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Positions;
import com.itaSS.entity.enumInfo.Technics;
import com.itaSS.utils.ConsoleInputReader;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public final class CriterionBuilder {

    public static Set<Criterion> getHallCriterion(Hall hall) {
        Set<Criterion> result = new HashSet<>();
        if (hall.getName() != null) {
            result.add(getStrokeCriterion("name", hall.getName()));
        }
        return result;
    }

    public static Set<Criterion> getExhibitCriterion(Exhibit exhibit) {
        Set<Criterion> result = new HashSet<>();
        if (exhibit.getName() != null) {
            result.add(getStrokeCriterion("name", exhibit.getName()));
        }
        if (exhibit.getAuthorName() != null) {
            result.add(getStrokeCriterion("authorName", exhibit.getAuthorName()));
        }
        if (exhibit.getCreationDate() != null ) {
            result.add(getDateCriterion("creationDate", exhibit.getCreationDate()));
        }
        if (exhibit.getArriveDate() != null) {
            result.add(getDateCriterion("arriveDate", exhibit.getArriveDate()));
        }
        if (exhibit.getMaterial() != null) {
            result.add(getEnumCriterion("material", Materials.class));
        }
        if (exhibit.getTechnic() != null) {
            result.add(getEnumCriterion("technic", Technics.class));
        }
        return result;
    }

    public static Set<Criterion> getTourCriterion(Tour tour) {
        Set<Criterion> result = new HashSet<>();
        if (tour.getTourName() != null) {
            result.add(getStrokeCriterion("tourName", tour.getTourName()));
        }
        if (tour.getBeginDate() != null) {
            result.add(getDateCriterion("beginDate", tour.getBeginDate()));
        }
        if (tour.getEndDate() != null) {
            result.add(getDateCriterion("endDate", tour.getEndDate()));
        }
        return result;
    }

    public static Set<Criterion> getWorkerCriterion(Worker worker) {
        Set<Criterion> result = new HashSet<>();
        if (worker.getFirstName() != null) {
            result.add(getStrokeCriterion("firstName", worker.getFirstName()));
        }
        if (worker.getLastName() != null) {
            result.add(getStrokeCriterion("lastName", worker.getLastName()));
        }
        if (worker.getSalary() != null) {
            result.add(getNumberCriterion("salary", worker.getSalary()));
        }
        if (worker.getPosition() != null) {
            result.add(getEnumCriterion("position", Positions.class));
        }
        return result;
    }

    private static Criterion getStrokeCriterion(String column, String input) {
        return Restrictions.like(column, input + "%");
    }

    private static Criterion getDateCriterion(String column, Date input)  {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Criterion criterion;
        criterion = Restrictions.eq(column, input);
        return criterion;
    }

    private static Criterion getNumberCriterion(String column, BigDecimal bigDecimal) {
        return Restrictions.eq(column, bigDecimal);
    }

    private static Criterion getEnumCriterion(String column, Class<? extends Enum<?>> type) {
        Enum<?> obj = ConsoleInputReader.selectEnum(type);
        return Restrictions.eq(column, obj);
    }

}

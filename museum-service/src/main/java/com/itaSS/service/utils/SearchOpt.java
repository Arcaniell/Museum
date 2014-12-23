package com.itaSS.service.utils;

import com.itaSS.dao.EntityManagerFact;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Positions;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import static com.itaSS.service.utils.RegExCheck.*;

public final class SearchOpt {

    //TODO read from DB
    private static final String[] hallAtrNames = {"name"};
    private static final String[] exhibitAtrNames = {"name", "authorName", "creationDate",
            "arriveDate", "material", "technic"};

    public static String[] getAttributes(Class<?> type) {
        String[] result;
        switch (type.getSimpleName()) {
            case "Hall":
                result = hallAtrNames;
                break;
            case "Exhibit":
                result = exhibitAtrNames;
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    public static<T> CriteriaQuery<T> getCQ(Class<T> type, String input) {
        String[] args = input.split(" ");
        String[] attributes = getAttributes(type);
        //TODO check 'attributes' for NULL

        CriteriaBuilder cb = EntityManagerFact.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        for (int i = 0; i < args.length; i++) {
            if (!args[i].equals("-")) {
                if (checkForDate(args[i])) {
                    cq.where(cb.equal(root.get(attributes[i]), getDate(args[i])));
                } else if (checkForEnumSelect(args[i])) {

                } else if (checkForNumber(args[i])) {

                } else {
                    cq.where(cb.like(root.get(attributes[i]), args[i]));
                }
            }
        }
        return cq;
    }

    public static Set<Criterion> getTourCriterion(Tour tour) {
        Set<Criterion> result = new HashSet<>();
        if (tour.getTourName() != null) {
            result.add(getStrokeCriterion("tourName", tour.getTourName()));
        }
        if (tour.getBeginDate() != null) {
//            result.add(getDateCriterion("beginDate", tour.getBeginDate()));
        }
        if (tour.getEndDate() != null) {
//            result.add(getDateCriterion("endDate", tour.getEndDate()));
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

    private static Date getDate(String input)  {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
        Date date = null;
        try {
            date = new Date(formatter.parse(input).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static Criterion getNumberCriterion(String column, BigDecimal bigDecimal) {
        return Restrictions.eq(column, bigDecimal);
    }

    private static Criterion getEnumCriterion(String column, Class<? extends Enum<?>> type) {
        Enum<?> obj = ConsoleInputReader.selectEnum(type);
        return Restrictions.eq(column, obj);
    }

}

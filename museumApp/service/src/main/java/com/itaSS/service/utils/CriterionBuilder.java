package com.itaSS.service.utils;

import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Positions;
import com.itaSS.entity.enumInfo.Technics;
import com.itaSS.utils.ConsoleInputReader;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public final class CriterionBuilder {

    private static int argCounter;

    public static Set<Criterion> getHallCriterion(String input) {
        Set<Criterion> result = new HashSet<>();
        String[] args = input.split(" ");
        argCounter = 0;
        if (!args[argCounter].equals("-")) {
            result.add(getStrokeCriterion("name", args[argCounter]));
        }
        return result;
    }

    public static Set<Criterion> getExhibitCriterion(String input) {
        Set<Criterion> result = new HashSet<>();
        String[] args = input.split(" ");
        argCounter = 0;
        if (!args[argCounter].equals("-")) {
            result.add(getStrokeCriterion("name", args[argCounter]));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(getStrokeCriterion("author_name", args[argCounter]));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(getDateCriterion("creation_date", args[argCounter]));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(getDateCriterion("arrive_date", args[argCounter]));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            System.out.println("Select material:");
            result.add(getEnumCriterion("material", Materials.class));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            System.out.println("Select technic:");
            result.add(getEnumCriterion("technic", Technics.class));
        }
        return result;
    }

    public static Set<Criterion> getTourCriterion(String input) {
        Set<Criterion> result = new HashSet<>();
        String[] args = input.split(" ");
        argCounter = 0;
        if (!args[argCounter].equals("-")) {
            result.add(getStrokeCriterion("tour_name", args[argCounter]));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(getDateCriterion("begin_Date", args[argCounter]));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(getDateCriterion("end_Date", args[argCounter]));
        }
        return result;
    }

    public static Set<Criterion> getWorkerCriterion(String input) {
        Set<Criterion> result = new HashSet<>();
        String[] args = input.split(" ");
        argCounter = 0;
        if (!args[argCounter].equals("-")) {
            result.add(getStrokeCriterion("firstName", args[argCounter]));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(getStrokeCriterion("lastName", args[argCounter]));
        }
        argCounter++;
        if(!args[argCounter].equals("-")) {
            result.add(getNumberCriterion("salary", args[argCounter]));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(getEnumCriterion("position", Positions.class));
        }
        return result;
    }

    private static Criterion getStrokeCriterion(String column, String input) {
        return Restrictions.like(column, input + "%");
    }

    private static Criterion getDateCriterion(String column, String input)  {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Criterion criterion = null;
        try {
            criterion = Restrictions.eq(column, new Date(dateFormat.parse(input).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return criterion;
    }

    private static Criterion getEnumCriterion(String column, Class<? extends Enum<?>> type) {
        Enum<?> obj = ConsoleInputReader.selectEnum(type);
        return Restrictions.eq(column, obj);
    }

    private static Criterion getNumberCriterion(String column, String input) {
        return Restrictions.eq(column, new BigDecimal(input));
    }

}

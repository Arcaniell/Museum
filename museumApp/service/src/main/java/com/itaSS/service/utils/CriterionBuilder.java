package com.itaSS.service.utils;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import java.sql.Date;

public final class CriterionBuilder {

    public static Set<Criterion> getExhibitCriterion(String input) {
        Set<Criterion> result = new HashSet<>();
        String[]args = input.split(" ");
        int argCounter = 0;
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
            result.add(Restrictions.like("material", args[argCounter] + "%"));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(Restrictions.like("technic", args[argCounter] + "%"));
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

    private static Criterion getEnumCriterion(String column) {

        return null;
    }

}

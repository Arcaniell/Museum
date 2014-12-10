package com.itaSS.utils;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.Set;

public final class CriterionBuilder {

    public static Set<Criterion> getExhibitCriterion(String input) {
        Set<Criterion> result = new HashSet<>();
        String[]args = input.split(" ");
        int argCounter = 0;
        if (!args[argCounter].equals("-")) {
            result.add(Restrictions.like("name", args[argCounter] + "%"));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(Restrictions.like("author_name", args[argCounter] + "%"));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(Restrictions.like("creation_date", args[argCounter] + "%"));
        }
        argCounter++;
        if (!args[argCounter].equals("-")) {
            result.add(Restrictions.like("arrive_date", args[argCounter] + "%"));
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
}

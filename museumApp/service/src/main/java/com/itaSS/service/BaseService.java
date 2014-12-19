package com.itaSS.service;

public class BaseService {
    protected final int many_results = 1;
    protected final int zero_result = 0;
    protected final String searchOptions = "\nEnter criteria for selecting: \n";
    private final String inputError = "Wrong input format!\nTry again:\n";

    protected boolean checkNumber(String input) {
        return checker(input, "-?\\d*\\.\\d*");
    }

    protected boolean checkName(String input) {
        return checker(input, "[A-Z][a-z]*");
    }

    protected boolean checkComplexName(String input) {
        return checker(input, "( ?\\w)+");
    }

    protected boolean checkDate(String input) {
        return checker(input, "\\d{4}-\\d{2}-\\d{2}");
    }

    private boolean checker(String input, String pattern) {
        if (!input.matches(pattern)) {
            System.err.println(inputError);
            return false;
        }
        return true;
    }

}

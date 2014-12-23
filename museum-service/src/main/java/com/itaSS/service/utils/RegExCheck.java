package com.itaSS.service.utils;

public class RegExCheck {

    private static final String inputError = "Wrong input format!\nTry again:\n";

    public static boolean checkForNumber(String input) {
        return checker(input, "-?\\d*\\.\\d*");
    }

    public static boolean checkForName(String input) {
        return checker(input, "[A-Z][a-z]*");
    }

    public static boolean checkForComplexName(String input) {
        return checker(input, "( ?\\w)+");
    }

    public static boolean checkForEnumSelect(String input) {
        return checker(input, "[y|n]");
    }

    public static boolean checkForDate(String input) {
        return checker(input, "\\d{4}-\\d{2}-\\d{2}");
    }

    private static boolean checker(String input, String pattern) {
        return input.matches(pattern);
    }
}

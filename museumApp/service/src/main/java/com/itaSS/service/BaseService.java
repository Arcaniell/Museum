package com.itaSS.service;

public class BaseService {
    protected final int firstElement = 0;
    protected final int many_results = 1;
    protected final int zero_result = 0;
    protected final String searchOptions = "Enter criteria for selecting in following format (\"-\" for skip): ";

    protected boolean checkNumber(String input) {
        return input.matches("[+|-]\\d*\\.\\d*");
    }

}

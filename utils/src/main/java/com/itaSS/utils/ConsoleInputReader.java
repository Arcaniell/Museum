package com.itaSS.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public final class ConsoleInputReader {
    private static BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        try {
            return inConsole.readLine();
        } catch (IOException e) {
            System.out.println("Wrong Input!");
            e.printStackTrace();
        }
        return null;
    }

    public static void close() throws IOException {
        inConsole.close();
    }

    public static Date readDate(String input) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return new Date(dateFormat.parse(input).getTime());
        } catch (ParseException e) {
            System.out.println("Parse err");
            e.printStackTrace();
        }
        return null;
    }

    public static Enum<?> selectEnum(Class<? extends Enum<?>> type) {
        int counter = 1;
        for (Enum<?> val : type.getEnumConstants()) {
            System.out.print("#" + (counter++) + " " +val.toString() + " \n");
        }
        System.out.println();
        Enum<?> material;
        String input = readLine();
        if (!input.equals("") && checkMenuSelect(input)
                && (Integer.valueOf(input) <= type.getEnumConstants().length)) {
            int selected = Integer.valueOf(input) - 1;
            material = Arrays.asList(type.getEnumConstants()).get(selected);
            return material;
        }
        return null;
    }

    private static boolean checkMenuSelect(String string) {
        return string.matches("[1-9][0-9]*");
    }

}

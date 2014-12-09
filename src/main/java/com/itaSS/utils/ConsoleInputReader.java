package com.itaSS.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}

package com.itaSS;

import com.itaSS.interact.Console;
import com.itaSS.utils.DataGenerators;

public class App {

    public static void main(String[] args) {
        DataGenerators.generateData();
        Console console = new Console();
        console.run();
        console.close();
    }
}

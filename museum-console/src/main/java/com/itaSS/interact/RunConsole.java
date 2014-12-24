package com.itaSS.interact;

import com.itaSS.service.DataGenerator;
import com.itaSS.service.implementation.DataGeneratorsImp;

public class RunConsole {
    private Console console = new Console();

    public void run() {
        console.run();
    }

    public void stop() {
        console.close();
    }

    public static void main(String[] args) {
//        DataGenerator dataGenerator = new DataGeneratorsImp();
//        dataGenerator.generateData();
//        RunConsole runConsole = new RunConsole();
//        runConsole.run();
//        runConsole.stop();
    }
}

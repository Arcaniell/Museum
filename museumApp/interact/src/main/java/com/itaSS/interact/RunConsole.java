package com.itaSS.interact;

import com.itaSS.utils.DataGenerators;

public class RunConsole {
    private Console console = new Console();

    public void run() {
        console.run();
    }

    public void stop() {
        console.close();
    }

    public static void main(String[] args) {
        DataGenerators.generateData();
        RunConsole runConsole = new RunConsole();
        runConsole.run();
        runConsole.stop();
    }
}
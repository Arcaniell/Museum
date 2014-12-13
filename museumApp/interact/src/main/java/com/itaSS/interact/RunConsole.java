package com.itaSS.interact;

import com.itaSS.dao.JobDao;
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
        JobDao jobDao = new JobDao();
        //=======
//        jobDao.setWorkerToHall();
        //=======
        runConsole.run();
        runConsole.stop();
    }
}

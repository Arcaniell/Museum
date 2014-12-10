package com.itaSS.service;

import com.itaSS.dao.WorkerDao;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Positions;

import java.math.BigDecimal;

import static com.itaSS.utils.ConsoleInputReader.readLine;
import static com.itaSS.utils.ConsoleInputReader.selectEnum;

public class WorkerService {
    public void addWorker() {
        System.out.println("Please enter required info: ");
        Worker worker = new Worker();
        System.out.println("\tWorker name");
        String name = readLine();
        worker.setName(name);
        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tWorker salary: ");
        String input = readLine();
        //TODO Salary format check
        if (!input.equals("")) {
            worker.setSalary(new BigDecimal(input));
        }
        System.out.println("\tPosition: ");
        Positions position = (Positions) selectEnum(Positions.class);
        if (position != null) {
            worker.setPosition(position);
        }
        WorkerDao workerDao = new WorkerDao(Worker.class);
        workerDao.create(worker);
    }
}

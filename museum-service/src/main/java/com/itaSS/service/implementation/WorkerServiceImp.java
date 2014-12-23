package com.itaSS.service.implementation;

import com.itaSS.dao.implementation.WorkerDaoImp;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Positions;
import com.itaSS.service.WorkerService;
import com.itaSS.service.utils.SearchOpt;
import org.hibernate.criterion.Criterion;

import java.math.BigDecimal;
import java.util.Set;

import static com.itaSS.service.utils.ConsoleInputReader.*;
import static com.itaSS.service.utils.RegExCheck.*;

public class WorkerServiceImp extends BaseServiceImp
        implements WorkerService{

    private static WorkerDaoImp workerDao = new WorkerDaoImp();

    public void addWorker() {
        Worker worker = enterWorkerInfo();
        workerDao.create(worker);
    }

    public void updateWorker() {
        System.out.println("ENTER INFO FOR WORKER TO UPDATE: ");
        Worker workerOld = searchWorker();
        System.out.println("ENTER NEW INFO: ");
        workerDao.update(enterWorkerInfo(workerOld));
    }

    public void deleteWorker() {
        System.out.println("ENTER INFO FOR WORKER TO DELETE");
        Worker worker = searchWorker();
        workerDao.remove(worker);
    }

    private Worker enterWorkerInfo() {
        return enterWorkerInfo(null);
    }

    private Worker enterWorkerInfo(Worker worker) {
        System.out.println("Please enter required info: ");
        if (worker == null) {
            worker = new Worker();
        }

        System.out.println("\tWorker first name: ");
        String name = readLine();
        if (!name.equals("")) {
            while (!checkForName(name)) {
                name = readLine();
            }
            worker.setFirstName(name);
        }

        System.out.println("\tWorker last name: ");
        String lastName = readLine();
        if (!lastName.equals("")) {
            while (!checkForName(lastName)) {
                lastName = readLine();
            }
            worker.setLastName(lastName);
        }

        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tWorker salary: ");
        String salary = readLine();
        if (!salary.equals("")) {
            while (!checkForNumber(salary)) {
                salary = readLine();
            }
            worker.setSalary(new BigDecimal(salary));
        }

        System.out.println("\tPosition: ");
        Positions position = (Positions) selectEnum(Positions.class);
        if (position != null) {
            worker.setPosition(position);
        }
        return worker;
    }

    public Worker searchWorker() {
        System.out.println(searchOptions);
        Worker searchExample = enterWorkerInfo();
        Set<Criterion> criteria = SearchOpt.getWorkerCriterion(searchExample);
        Set<Worker> workers = workerDao.getSpecEntity(criteria);
        int result_size = workers.size();
        while (result_size == zero_result || result_size > many_results ) {
            if (result_size == zero_result) {
                System.out.println("No mathces found, try again!");
                System.out.println(searchOptions);
            } else {
                System.out.println(workers);
                System.out.println("More then single result, enter search criteria again: ");
                System.out.println(searchOptions);
            }
            searchExample = enterWorkerInfo();
            criteria = SearchOpt.getWorkerCriterion(searchExample);
            workers = workerDao.getSpecEntity(criteria);
            result_size = workers.size();
        }
        return (workers.size() == 0) ? null : workers.iterator().next();
    }

    public void setWorkerToHall() {
        Worker worker = searchWorker();

        HallServiceImp hallService = new HallServiceImp();
        Hall hall = hallService.searchHall();

        workerDao.setWorkerToJob(worker.getId(), hall.getId());
    }

    public void setWorkerToTour() {
        Worker worker = searchWorker();

        TourServiceImp tourService = new TourServiceImp();
        Tour tour = tourService.searchTour();

        workerDao.setWorkerToJob(worker.getId(), tour.getId());
    }
}

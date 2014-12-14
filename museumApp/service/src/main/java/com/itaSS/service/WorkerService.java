package com.itaSS.service;

import com.itaSS.dao.WorkerDao;
import com.itaSS.entity.Hall;
import com.itaSS.entity.Tour;
import com.itaSS.entity.Worker;
import com.itaSS.entity.enumInfo.Positions;
import com.itaSS.service.utils.CriterionBuilder;
import org.hibernate.criterion.Criterion;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static com.itaSS.utils.ConsoleInputReader.readLine;
import static com.itaSS.utils.ConsoleInputReader.selectEnum;

public class WorkerService extends BaseService{

    private static WorkerDao workerDao = new WorkerDao(Worker.class);

    public void addWorker() {
        Worker worker = enterWorkerInfo();
        workerDao.create(worker);
    }

    public void updateWorker() {
        Worker workerOld = searchWorker();
        workerDao.update(enterWorkerInfo(workerOld));
    }

    public void deleteWorker() {
        System.out.println("ENTER INFO FOR WORKER TO DELETE");
        Worker worker = searchWorker();
        workerDao.delete(worker);
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
        worker.setFirstName(name);

        System.out.println("\tWorker last name: ");
        name = readLine();
        worker.setLastName(name);

        System.out.println("Enter additional info, or leave it blank: ");
        System.out.println("\tWorker salary: ");
        String input = readLine();
        if (!input.equals("")) {
            worker.setSalary(new BigDecimal(input));
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
        System.out.println("\tfirst_name last_name salary position");
        String input = readLine();
        Set<Criterion> criteria = CriterionBuilder.getWorkerCriterion(input);
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
            input = readLine();
            criteria = CriterionBuilder.getWorkerCriterion(input);
            workers = workerDao.getSpecEntity(criteria);
            result_size = workers.size();
        }
        return (workers.size() == 0) ? null : workers.iterator().next();
    }

    public void setWorkerToHall() {
        Worker worker = searchWorker();

        HallService hallService = new HallService();
        Hall hall = hallService.searchHall();

        workerDao.setWorkerToJob(worker, hall);
    }

    public void setWorkerToTour() {
        Worker worker = searchWorker();

        TourService tourService = new TourService();
        Tour tour = tourService.searchTour();

        WorkerDao workerDao = new WorkerDao(Worker.class);
        workerDao.setWorkerToJob(worker, tour);
    }
}

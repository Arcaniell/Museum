package com.itaSS.interact;

import com.itaSS.dao.SessionFact;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.service.ExhibitService;
import com.itaSS.service.HallService;
import com.itaSS.service.TourService;
import com.itaSS.service.WorkerService;
import com.itaSS.utils.ConsoleInputReader;

import java.io.IOException;

import static com.itaSS.utils.ConsoleInputReader.selectEnum;

public class Console {

    public void run() {
        Enum<Actions> selected = (Actions) selectEnum(Actions.class);
        if (selected == Actions.ADD_EXHIBIT) {
            addExhibit();
        } else if (selected == Actions.ADD_HALL) {
            addHall();
        } else if (selected == Actions.ADD_TOUR) {
            addTour();
        } else if (selected == Actions.ADD_WORKER) {
            addWorker();
        } else if (selected == Actions.SET_EXHIBIT_TO_HALL) {
            setExhibitToHall();
        } else if (selected == Actions.SET_HALLS_TO_TOUR) {
            setHallsToTour();
        } else if (selected == Actions.SET_WORKER_TO_HALL) {
            setWorkerToHall();
        } else if (selected == Actions.SET_WORKER_TO_TOUR) {
            setWorkerToTour();
        }
    }

    private void addExhibit() {
        ExhibitService exhibitService = new ExhibitService();
        exhibitService.addExhibit();
    }

    private void addHall() {
        HallService hallService = new HallService();
        hallService.addHall();
    }

    private void addTour() {
        TourService tourService = new TourService();
        tourService.addTour();
    }

    private void addWorker() {
        WorkerService workerService = new WorkerService();
        workerService.addWorker();
    }

    private void setExhibitToHall() {
        ExhibitService exhibitService = new ExhibitService();
        Exhibit exhibit = exhibitService.searchExhibit();

        HallService hallService = new HallService();
        Hall hall = hallService.searchHall();

        exhibitService.setExhibitToHall(exhibit, hall);
    }

    private void setHallsToTour() {
        HallService hallService = new HallService();
        hallService.setHallsToTour();
    }

    //TODO Ask Nazar for base class for some Entities
    private void setWorkerToHall() {
        WorkerService workerService = new WorkerService();
        workerService.setWorkerToHall();
    }

    private void setWorkerToTour() {
        WorkerService workerService = new WorkerService();
        workerService.setWorkerToTour();
    }

    public void close() {
        try {
            SessionFact.closeFactory();
            ConsoleInputReader.close();
        } catch (IOException e) {
            System.out.println("Error while closing Console Resources!!!");
            e.printStackTrace();
        }
    }

}

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

    private ExhibitService exhibitService = new ExhibitService();
    private HallService hallService = new HallService();
    private TourService tourService = new TourService();
    private WorkerService workerService = new WorkerService();

    public void run() {
        Enum<Actions> selected = (Actions) selectEnum(Actions.class);
        if (selected == Actions.ADD_EXHIBIT) {
            exhibitService.addExhibit();
        } else if (selected == Actions.UPDATE_EXHIBIT) {
            exhibitService.updateExhibit();
        } else if (selected == Actions.ADD_HALL) {
            hallService.addHall();
        } else if (selected == Actions.UPDATE_HALL) {
            hallService.updateHall();
        } else if (selected == Actions.ADD_TOUR) {
            tourService.addTour();
        } else if (selected == Actions.UPDATE_TOUR) {
            tourService.updateTour();
        }  else if (selected == Actions.ADD_WORKER) {
            workerService.addWorker();
        } else if (selected == Actions.UPDATE_WORKER) {
            workerService.updateWorker();
        }  else if (selected == Actions.SET_EXHIBIT_TO_HALL) {
            setExhibitToHall();
        } else if (selected == Actions.SET_HALLS_TO_TOUR) {
            hallService.setHallsToTour();
        } else if (selected == Actions.SET_WORKER_TO_HALL) {
            workerService.setWorkerToHall();
        } else if (selected == Actions.SET_WORKER_TO_TOUR) {
            workerService.setWorkerToTour();
        }
    }

    private void setExhibitToHall() {
        ExhibitService exhibitService = new ExhibitService();
        Exhibit exhibit = exhibitService.searchExhibit();

        HallService hallService = new HallService();
        Hall hall = hallService.searchHall();

        exhibitService.setExhibitToHall(exhibit, hall);
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

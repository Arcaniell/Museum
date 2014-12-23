package com.itaSS.interact;

import com.itaSS.dao.EntityManagerFact;
import com.itaSS.dao.implementation.SessionFact;
import com.itaSS.entity.Exhibit;
import com.itaSS.entity.Hall;
import com.itaSS.service.implementation.ExhibitServiceImp;
import com.itaSS.service.implementation.HallServiceImp;
import com.itaSS.service.implementation.TourServiceImp;
import com.itaSS.service.implementation.WorkerServiceImp;
import com.itaSS.service.utils.ConsoleInputReader;

import java.io.IOException;

import static com.itaSS.service.utils.ConsoleInputReader.selectEnum;

public class Console {

    private ExhibitServiceImp exhibitService = new ExhibitServiceImp();
    private HallServiceImp hallService = new HallServiceImp();
    private TourServiceImp tourService = new TourServiceImp();
    private WorkerServiceImp workerService = new WorkerServiceImp();

    public void run() {
        Enum<Actions> selected = (Actions) selectEnum(Actions.class);
        while (selected != Actions.EXIT) {
            if (selected == Actions.ADD_EXHIBIT) {
                exhibitService.addExhibit();
            } else if (selected == Actions.UPDATE_EXHIBIT) {
                exhibitService.updateExhibit();
            } else if (selected == Actions.DELETE_EXHIBIT) {
                exhibitService.deleteExhibit();
            } else if (selected == Actions.ADD_HALL) {
                hallService.addHall();
            } else if (selected == Actions.UPDATE_HALL) {
                hallService.updateHall();
            } else if (selected == Actions.DELETE_HALL) {
                hallService.deleteHall();
            } else if (selected == Actions.ADD_TOUR) {
                tourService.addTour();
            } else if (selected == Actions.UPDATE_TOUR) {
                tourService.updateTour();
            } else if (selected == Actions.DELETE_TOUR) {
                tourService.deleteTour();
            } else if (selected == Actions.ADD_WORKER) {
                workerService.addWorker();
            } else if (selected == Actions.UPDATE_WORKER) {
                workerService.updateWorker();
            } else if (selected == Actions.DELETE_WORKER) {
                workerService.deleteWorker();
            } else if (selected == Actions.SET_EXHIBIT_TO_HALL) {
                setExhibitToHall();
            } else if (selected == Actions.SET_HALLS_TO_TOUR) {
                hallService.setHallsToTour();
            } else if (selected == Actions.SET_WORKER_TO_HALL) {
                workerService.setWorkerToHall();
            } else if (selected == Actions.SET_WORKER_TO_TOUR) {
                workerService.setWorkerToTour();
            } else if (selected == Actions.SHOW_HALLS_IN_TOUR) {
                tourService.showHallsFromTour();
            } else if (selected == Actions.SHOW_EXHIBITS_IN_TOUR) {
                tourService.showExhibitsFromTour();
            } else if (selected == Actions.SHOW_TOURS_FOR_HALL) {
                hallService.showToursForHall();
            } else if (selected == Actions.SHOW_TOURS_FOR_EXHIBIT) {
                exhibitService.showToursForExhibit();
            }
            selected = (Actions) selectEnum(Actions.class);
        }
    }

    private void setExhibitToHall() {
        ExhibitServiceImp exhibitService = new ExhibitServiceImp();
        Exhibit exhibit = exhibitService.searchExhibit();

        HallServiceImp hallService = new HallServiceImp();
        Hall hall = hallService.searchHall();

        exhibitService.setExhibitToHall(exhibit, hall);
    }

    public void close() {
        try {
            SessionFact.closeFactory();
            ConsoleInputReader.close();
            EntityManagerFact.close();
        } catch (IOException e) {
            System.out.println("Error while closing Console Resources!!!");
            e.printStackTrace();
        }
    }

}

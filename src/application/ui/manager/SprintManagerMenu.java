package application.ui.manager;

import bussinesslayer.entity.report.ReportSprint;
import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.report.reportsprint.IReportSprintService;
import bussinesslayer.service.report.reportsprint.ReportSprintService;
import bussinesslayer.service.sapce.sprint.ISprintService;
import bussinesslayer.service.sapce.sprint.SprintService;

import java.time.LocalDate;
import java.time.LocalTime;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class SprintManagerMenu {
    // -------------------- Properties ------------------------
    private ISprintService sprintService = new SprintService();
    private IReportSprintService reportSprintService = new ReportSprintService();
    private int projectId;
    public enum CHOICE_SPRINT_MANAGER_MENU {
        EXIT,
        CREATE_SPRINT,
        UPDATE_SPRINT,
        DELETE_SPRINT,
        VIEW_SPRINT,
        VIEW_REPORT,
        TASK_MANAGER
    }
    // -------------------- Constructor ------------------------

    public SprintManagerMenu(int projectId) throws Exception {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------

    // -------------------- Methods ------------------------
    public void processMenuForSprintManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Sprint Manager Menu");
            printValueMenu("Manager\\Project\\Sprint");
            for (CHOICE_SPRINT_MANAGER_MENU choice : CHOICE_SPRINT_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_SPRINT_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_SPRINT_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_SPRINT -> this.createSprint();
                        case UPDATE_SPRINT -> this.updateSprint();
                        case DELETE_SPRINT -> this.deleteSprint();
                        case VIEW_SPRINT -> this.viewSprint();
                        case VIEW_REPORT -> this.viewReport();
                        case TASK_MANAGER -> this.processMenuForTaskManager();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createSprint() throws Exception {
        String name = readString("Name: ");
        String description = readString("Description: ");
        LocalDate startDate = readLocalDate("Start date: ");
        LocalDate endDate = readLocalDate("End date: ");
        Sprint sprint = new Sprint(name, description, startDate, endDate, projectId);
        sprintService.createSprint(sprint);
    }
    private void updateSprint() throws Exception {
        int sprintId = readInt("Enter sprint id: ");
        Sprint sprint = sprintService.getById(sprintId);
        sprint.setName(readString("Enter new sprint name: "));
        sprint.setDescription(readString("Enter new sprint description: "));
        sprint.setStartDate(readLocalDate("Enter new sprint start date: "));
        sprint.setEndDate(readLocalDate("Enter new sprint end date: "));
        sprintService.update(sprint);
    }
    private void deleteSprint() throws Exception {
        int sprintId = readInt("Enter sprint id: ");
        sprintService.delete(sprintId);
    }
    private void viewSprint() throws Exception {
        sprintService.viewById(projectId);
    }
    private void viewReport() throws Exception {
        int sprintId = readInt("Enter sprint id: ");
        sprintService.viewReport(sprintId);
    }
    private void processMenuForTaskManager() throws Exception {
        int sprintId = readInt("Enter sprint id: ");
        TaskMangerMenu taskMangerMenu = new TaskMangerMenu(sprintId);
        taskMangerMenu.processMenuForTaskManager();
    }
}

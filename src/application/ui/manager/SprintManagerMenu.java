package application.ui.manager;

import bussinesslayer.entity.report.ReportSprint;
import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.report.reportsprint.IReportSprintService;
import bussinesslayer.service.report.reportsprint.ReportSprintService;
import bussinesslayer.service.sapce.sprint.ISprintService;
import bussinesslayer.service.sapce.sprint.SprintService;

import java.time.LocalDate;
import java.util.List;

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
        waitForInput();
    }
    private void createSprint() {
        try {
            String name = readString("Name: ");
            String description = readString("Description: ");
            LocalDate startDate = readLocalDate("Start date: ");
            LocalDate endDate = readLocalDate("End date: ");
            Sprint sprint = new Sprint(name, description, startDate, endDate, projectId);
            sprintService.create(sprint);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void updateSprint() {
        try {
            int sprintId = readInt("Enter sprint id: ");
            Sprint sprint = sprintService.getById(sprintId);
            if (sprint.getProjectId() == projectId) {
                sprint.setName(readString("Enter new sprint name: "));
                sprint.setDescription(readString("Enter new sprint description: "));
                sprint.setStartDate(readLocalDate("Enter new sprint start date: "));
                sprint.setEndDate(readLocalDate("Enter new sprint end date: "));
                sprintService.update(sprint);
            } else {
                printValueln("Sprint is not in this project.");
            }
        } catch (Exception e) {
            printValue(e.getMessage());
        }

    }
    private void deleteSprint() {
        try {
            int sprintId = readInt("Enter sprint id: ");
            if (sprintService.getById(sprintId).getProjectId() == projectId) {
                sprintService.delete(sprintId);
            } else {
                printValueln("Sprint is not in this project.");
            }
        } catch (Exception e) {
            printValue(e.getMessage());
        }
    }
    private void viewSprint() {
        try {
            List<Sprint> sprintList = sprintService.getAllSprint(projectId);
            for (Sprint sprint : sprintList) {
                printValue("id: " + sprint.getId() + " ".repeat(40 - String.valueOf(sprint.getId()).length()) + "|");
                printValue("name: " + sprint.getName() + " ".repeat(40 - String.valueOf(sprint.getName()).length()) + "|");
                printValue("start date: " + sprint.getStartDate() + " ".repeat(40 - String.valueOf(sprint.getStartDate()).length()) + "|");
                printValue("end date: " + sprint.getEndDate() + " ".repeat(40 - String.valueOf(sprint.getEndDate()).length()) + "|");
                printValueln("description: " + sprint.getDescription() + " ".repeat(40 - String.valueOf(sprint.getDescription()).length()) + "|");
            }
        } catch (Exception e) {
            printValue(e.getMessage());
        }
    }
    private void viewReport() throws Exception {
        try {
            int projectId = readInt("Enter project id: ");
            Sprint sprint = sprintService.getById(projectId);
            if (sprint.getProjectId() == projectId) {
                List<ReportSprint> list = reportSprintService.getReports(projectId);
                for (ReportSprint reportSprint : list) {
                    printLineSeparate();
                    printValue("id: " + reportSprint.getId() + " ".repeat(40 - String.valueOf(reportSprint.getId()).length()) + "|");
                    printValue("time: " + reportSprint.getTime() + " ".repeat(40 - String.valueOf(reportSprint.getTime()).length()) + "|");
                    printValue("date: " + reportSprint.getDate() + " ".repeat(40 - String.valueOf(reportSprint.getDate()).length()) + "|");
                    printValue("description: " + reportSprint.getDescription() + " ".repeat(40 - String.valueOf(reportSprint.getDescription()).length()) + "|");
                    printValueln("sprint id: " + reportSprint.getSprintId() + " ".repeat(40 - String.valueOf(reportSprint.getSprintId()).length()) + "|");
                }
            }
        } catch (Exception e) {
            printValue(e.getMessage());
        }
    }
    private void processMenuForTaskManager() throws Exception {
        int sprintId = readInt("Enter sprint id: ");
        TaskMangerMenu taskMangerMenu = new TaskMangerMenu(sprintId);
        taskMangerMenu.processMenuForTaskManager();
    }
}

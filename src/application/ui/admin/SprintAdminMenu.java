package application.ui.admin;

import bussinesslayer.entity.report.ReportSprint;
import bussinesslayer.service.report.reportsprint.IReportSprintService;
import bussinesslayer.service.report.reportsprint.ReportSprintService;
import bussinesslayer.service.sapce.sprint.ISprintService;
import bussinesslayer.service.sapce.sprint.SprintService;

import java.time.LocalDate;
import java.time.LocalTime;

import static application.utilities.InputUtil.readInt;
import static application.utilities.InputUtil.readString;
import static application.utilities.OutputUtil.*;
import static application.utilities.OutputUtil.printValueln;

public class SprintAdminMenu {
    private final ISprintService serviceSprint = new SprintService();
    private final IReportSprintService reportSprintService = new ReportSprintService();
    public SprintAdminMenu() throws Exception {
    }

    public enum CHOICE_SPRINT_ADMIN_MENU {
        EXIT,
        VIEW_SPRINT,
        CREATE_REPORT,
        VIEW_REPORT
    }

    public void processMenuBacklogAdmin() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Report Project Admin Menu");
            for (CHOICE_SPRINT_ADMIN_MENU choice : CHOICE_SPRINT_ADMIN_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_SPRINT_ADMIN_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_SPRINT_ADMIN_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_REPORT -> this.createReport();
                        case VIEW_REPORT -> this.viewReport();
                        case VIEW_SPRINT -> this.viewSprint();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createReport() throws Exception {
        int sprintId = readInt("Enter sprint id: ");
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        String description = readString("Enter description: ");
        ReportSprint reportSprint = new ReportSprint(time, date, description, sprintId);
        reportSprintService.create(reportSprint);
    }
    private void viewReport() throws Exception {
        reportSprintService.viewAll();
    }
    private void viewSprint() throws Exception {
        serviceSprint.viewAll();
    }
}
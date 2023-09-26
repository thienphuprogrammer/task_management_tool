package application.ui.admin;

import bussinesslayer.entity.report.ReportSprint;
import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.report.reportsprint.IReportSprintService;
import bussinesslayer.service.report.reportsprint.ReportSprintService;
import bussinesslayer.service.sapce.sprint.ISprintService;
import bussinesslayer.service.sapce.sprint.SprintService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
            printValueln("Admin\\Sprint");
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
                        case VIEW_REPORT -> this.viewReports();
                        case VIEW_SPRINT -> this.viewSprint();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
        waitForInput();
    }
    private void createReport()  {
        try {
            int sprintId = readInt("Enter sprint id: ");
            Sprint sprint = serviceSprint.getById(sprintId);
            if (sprint != null) {
                LocalTime time = LocalTime.now();
                LocalDate date = LocalDate.now();
                String description = readString("Enter description: ");
                ReportSprint reportSprint = new ReportSprint(time, date, description, sprintId);
                reportSprintService.create(reportSprint);
            } else {
                printValueln("Invalid sprint id.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewReports() {
        try {
            List<ReportSprint> reportSprint = reportSprintService.getAll();
            for (ReportSprint r : reportSprint) {
                printValue("id: " + r.getId() + "| ");
                printValue("Time: " + r.getTime() + "| ");
                printValue("Date: " + r.getDate() + "| ");
                printValue("Description: " + r.getDescription() + "| ");
                printValueln("Sprint id: " + r.getSprintId() + "| ");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewSprint() throws Exception {
        try {
            List<Sprint> sprints = serviceSprint.getAll();
            for (Sprint r : sprints) {
                printValue("id: " + r.getId() + " ".repeat(30 - String.valueOf(r.getId()).length()) + "| ");
                printValue("name: " + r.getName() + " ".repeat(30 - String.valueOf(r.getName()).length()) + "| ");
                printValue("start date: " + r.getStartDate() + " ".repeat(30 - String.valueOf(r.getStartDate()).length()) + "| ");
                printValue("end date: " + r.getEndDate() + " ".repeat(30 - String.valueOf(r.getEndDate()).length()) + "| ");
                printValue("description: " + r.getDescription() + " ".repeat(30 - String.valueOf(r.getDescription()).length()) + "| ");
                printValueln("Project id: " + r.getProjectId() + " ".repeat(30 - String.valueOf(r.getProjectId()).length()) + "| ");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

package application.ui.admin;

import bussinesslayer.entity.report.ReportBacklog;
import bussinesslayer.service.report.reportbacklog.IReportBacklogService;
import bussinesslayer.service.report.reportbacklog.ReportBacklogService;
import bussinesslayer.service.sapce.backog.BacklogService;
import bussinesslayer.service.sapce.backog.IBacklogService;

import java.time.LocalDate;
import java.time.LocalTime;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class BacklogManagerMenu {
    private final IBacklogService serviceBacklog = new BacklogService();
    private final IReportBacklogService reportBacklogService = new ReportBacklogService();

    public BacklogManagerMenu() throws Exception {
    }

    public enum CHOICE_BACKLOG_ADMIN_MENU {
        EXIT,
        VIEW_TASK_IN_BACKLOG,
        CREATE_REPORT,
        VIEW_REPORT
    }

    public void processMenuBacklogAdmin() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Report Project Admin Menu");
            for (CHOICE_BACKLOG_ADMIN_MENU choice : CHOICE_BACKLOG_ADMIN_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_BACKLOG_ADMIN_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_BACKLOG_ADMIN_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_REPORT -> this.createReport();
                        case VIEW_REPORT -> this.viewReport();
                        case VIEW_TASK_IN_BACKLOG -> this.viewBacklog();
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
        int backlogId = readInt("Enter backlog id: ");
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        String description = readString("Enter description: ");
        ReportBacklog reportBacklog = new ReportBacklog(time, date, description, backlogId);
        reportBacklogService.create(reportBacklog);
    }
    private void viewReport() throws Exception {
        reportBacklogService.viewAll();
    }
    private void viewBacklog() throws Exception {
        serviceBacklog.viewAll();
    }
}

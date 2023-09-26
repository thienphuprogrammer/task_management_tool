package application.ui.admin;

import bussinesslayer.entity.report.ReportBacklog;
import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Task;
import bussinesslayer.service.report.reportbacklog.IReportBacklogService;
import bussinesslayer.service.report.reportbacklog.ReportBacklogService;
import bussinesslayer.service.sapce.backog.BacklogService;
import bussinesslayer.service.sapce.backog.IBacklogService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class BacklogAdminMenu {
    private final IBacklogService serviceBacklog = new BacklogService();
    private final IReportBacklogService reportBacklogService = new ReportBacklogService();

    public BacklogAdminMenu() throws Exception {
    }

    public enum CHOICE_BACKLOG_ADMIN_MENU {
        EXIT,
        VIEW_TASK_IN_BACKLOG,
        CREATE_REPORT,
        VIEW_REPORT,
        VIEW_ALL_BACKLOG
    }

    public void processMenuForBacklogAdmin() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Report Project Admin Menu");
            printValueMenu("Admin\\Backlog");
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
                        case VIEW_TASK_IN_BACKLOG -> this.viewTasksInBacklog();
                        case VIEW_ALL_BACKLOG -> this.viewAllBacklog();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
            waitForInput();
        }
    }
    private void createReport() {
        try {
            int backlogId = readInt("Enter backlog id: ");
            Backlog backlog = serviceBacklog.getById(backlogId);
            if (backlog != null) {
                LocalTime time = LocalTime.now();
                LocalDate date = LocalDate.now();
                String description = readString("Enter description: ");
                ReportBacklog reportBacklog = new ReportBacklog(time, date, description, backlogId);
                reportBacklogService.create(reportBacklog);
            } else {
                printValueln("Invalid backlog id.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    private void viewReport() throws Exception {
        try {
            List<ReportBacklog> reportBacklogs = reportBacklogService.getAll();
            for (ReportBacklog reportBacklog : reportBacklogs) {
                printValue("id: " + reportBacklog.getId() + "| ");
                printValue("Date: " + reportBacklog.getDate() + "| ");
                printValue("Time: " + reportBacklog.getTime() + "| ");
                printValue("Description: " + reportBacklog.getDescription() + "| ");
                printValueln("backlog id: " + reportBacklog.getBacklogId());
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    private void viewTasksInBacklog() throws Exception {
        try {
            int backlogId = readInt("Enter backlog id: ");
            List<Task> list = serviceBacklog.getTasksInBacklog(backlogId);
            for (Task task : list) {
                printValue("id: " + task.getId() + "| ");
                printValue("name: " + task.getName() + "| ");
                printValue("description: " + task.getDescription() + "| ");
                printValue("start date: " + task.getStartDate() + "| ");
                printValue("end date: " + task.getEndDate() + "| ");
                printValue("member id: " + task.getMemberId() + "| ");
                printValue("sprint id: " + task.getSprintId() + "| ");
                printValue("status: " + task.getStatus() + "| ");
                printValueln("backlog id: " + task.getBacklogId());
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllBacklog() throws Exception {
        try {
            List<Backlog> backlogs = serviceBacklog.getAll();
            for (Backlog backlog : backlogs) {
                printValue("id: " + backlog.getId() + "| ");
                printValueln("project id: " + backlog.getProjectId());

            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

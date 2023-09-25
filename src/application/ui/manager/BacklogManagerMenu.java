package application.ui.manager;

import bussinesslayer.entity.report.ReportBacklog;
import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.report.reportbacklog.IReportBacklogService;
import bussinesslayer.service.report.reportbacklog.ReportBacklogService;
import bussinesslayer.service.sapce.backog.BacklogService;
import bussinesslayer.service.sapce.backog.IBacklogService;
import bussinesslayer.service.user.IUserService;

import java.time.LocalDate;
import java.time.LocalTime;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class BacklogManagerMenu {
    // -------------------- Properties ------------------------
    private IBacklogService serviceBacklog = new BacklogService();
    private IReportBacklogService serviceReportBacklog  = new ReportBacklogService();
    private int projectId;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        CREATE_BACKLOG,
        UPDATE_BACKLOG,
        DELETE_BACKLOG,
        VIEW_BACKLOG,
        CREATE_REPORT_BACKLOG,
        VIEW_REPORT_BACKLOG,
        UPDATE_REPORT_BACKLOG
    }
    // -------------------- Constructor ------------------------
    public BacklogManagerMenu(int projectId) throws Exception {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------

    // -------------------- Methods ------------------------

    public void processMenuForBacklogManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Backlog Manager Menu");
            printValueMenu("Manager\\Project\\Backlog");
            for (CHOICE_BACKLOG_MANAGER_MENU choice : CHOICE_BACKLOG_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_BACKLOG_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_BACKLOG_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_BACKLOG -> this.createBacklog();
                        case UPDATE_BACKLOG -> this.updateBacklog();
                        case DELETE_BACKLOG -> this.deleteBacklog();
                        case VIEW_BACKLOG -> this.viewBacklog();
                        case VIEW_REPORT_BACKLOG -> this.viewReportBacklog();
                        case UPDATE_REPORT_BACKLOG -> this.updateReportBacklog();
                        case CREATE_REPORT_BACKLOG -> this.createReportBacklog();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createBacklog() throws Exception {
        int projectId = readInt("Project ID: ");
        String title = readString("Title: ");
        String description = readString("Description: ");
        String fileUrl = readString("File URL: ");
        Backlog backlog = new Backlog(title, description, fileUrl, projectId);
        serviceBacklog.create(backlog);
    }
    private void updateBacklog() throws Exception {
        int backlogId = readInt("Backlog ID: ");
        Backlog backlog = serviceBacklog.getById(backlogId);
        backlog.setTitle(readString("Title: "));
        backlog.setDescription(readString("Description: "));
        backlog.setFileURL(readString("File URL: "));
        serviceBacklog.update(backlog);
    }
    private void deleteBacklog() throws Exception {
        serviceBacklog.delete(readInt("Backlog ID: "));
    }
    private void viewBacklog() throws Exception {
        serviceBacklog.viewBacklogByProjectId(projectId);
    }
    private void viewReportBacklog() throws Exception {
        serviceReportBacklog.viewReport(projectId);
    }
    private void updateReportBacklog() throws Exception {
        int backlogId = readInt("Backlog ID: ");
        String description = readString("Description: ");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        ReportBacklog reportBacklog = new ReportBacklog(time, date, description, backlogId, 0);
        serviceReportBacklog.updateReportBacklog(reportBacklog);
    }

    private void createReportBacklog() throws Exception {
        int backlogId = readInt("Backlog ID: ");
        String description = readString("Description: ");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        ReportBacklog reportBacklog = new ReportBacklog(time, date, description, backlogId, 0);
        serviceReportBacklog.createReportBacklog(reportBacklog);
    }
}

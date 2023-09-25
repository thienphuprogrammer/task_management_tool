package application.ui.admin;

import bussinesslayer.entity.report.ReportProject;
import bussinesslayer.service.report.reportproject.IReportProjectService;
import bussinesslayer.service.report.reportproject.ReportProjectService;
import bussinesslayer.service.sapce.project.IProjectService;
import bussinesslayer.service.sapce.project.ProjectService;

import java.time.LocalTime;
import java.time.LocalDate;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class ProjectAdminMenu {
    private final IProjectService serviceProject = new ProjectService();
    private final IReportProjectService reportProjectService = new ReportProjectService();

    public ProjectAdminMenu() throws Exception {
    }

    public enum CHOICE_PROJECT_ADMIN_MENU {
        EXIT,
        VIEW_PROJECT,
        CREATE_REPORT,
        VIEW_REPORT
    }

    public void processMenuForReportProjectAdmin() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Report Project Admin Menu");
            for (CHOICE_PROJECT_ADMIN_MENU choice : CHOICE_PROJECT_ADMIN_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_PROJECT_ADMIN_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_PROJECT_ADMIN_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_REPORT -> this.createReport();
                        case VIEW_REPORT -> this.viewReport();
                        case VIEW_PROJECT -> this.viewProject();
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
        int projectId = readInt("Enter project id: ");
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        String description = readString("Enter description: ");
        ReportProject reportProject = new ReportProject(time, date, description, projectId);
        reportProjectService.create(reportProject);
    }
    private void viewReport() throws Exception {
        reportProjectService.viewAll();
    }
    private void viewProject() throws Exception {
        serviceProject.viewAll();
    }
}
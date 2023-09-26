package application.ui.admin;

import bussinesslayer.entity.report.ReportProject;
import bussinesslayer.entity.space.Project;
import bussinesslayer.service.report.reportproject.IReportProjectService;
import bussinesslayer.service.report.reportproject.ReportProjectService;
import bussinesslayer.service.sapce.project.IProjectService;
import bussinesslayer.service.sapce.project.ProjectService;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

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
            waitForInput();
        }
    }
    private void createReport() throws Exception {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getById(projectId);
            if (project != null) {
                LocalTime time = LocalTime.now();
                LocalDate date = LocalDate.now();
                String description = readString("Enter description: ");
                ReportProject reportProject = new ReportProject(time, date, description, projectId);
                reportProjectService.create(reportProject);
            } else {
                printValueln("Project not found.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewReport() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getById(projectId);
            if (project != null) {
                List<ReportProject> reportProjects = reportProjectService.getReportsProject(projectId);
                for (ReportProject reportProject : reportProjects) {
                    printValue("id: " + reportProject.getId() + " ".repeat(30 - String.valueOf(reportProject.getId()).length()) + "|");
                    printValue("time: " + reportProject.getTime() + " ".repeat(30 - String.valueOf(reportProject.getTime()).length()) + "|");
                    printValue("date: " + reportProject.getDate() + " ".repeat(30 - String.valueOf(reportProject.getDate()).length()) + "|");
                    printValue("description: " + reportProject.getDescription() + " ".repeat(30 - String.valueOf(reportProject.getDescription()).length()) + "|");
                    printValueln("Project id: " + reportProject.getProject_id() + " ".repeat(30 - String.valueOf(reportProject.getProject_id()).length()) + "|");
                }
            } else {
                printValueln("Project not found.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewProject() {
        try {
            List<Project> projects = serviceProject.getAll();
            for (Project project : projects) {
                printValue("id: " + project.getId() + " ".repeat(20 - String.valueOf(project.getId()).length()) + "|");
                printValue("name: " + project.getName() + " ".repeat(20 - String.valueOf(project.getName()).length()) + "|");
                printValue("start date: " + project.getStartDate() + " ".repeat(20 - String.valueOf(project.getStartDate()).length()) + "|");
                printValue("end date: " + project.getEndDate() + " ".repeat(20 - String.valueOf(project.getEndDate()).length()) + "|");
                printValue("description: " + project.getDescription() + " ".repeat(20 - String.valueOf(project.getDescription()).length()) + "|");
                printValueln("Manager id: " + project.getManagerId() + " ".repeat(20 - String.valueOf(project.getManagerId()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

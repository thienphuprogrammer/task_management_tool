package application.ui.manager;

import bussinesslayer.entity.report.ReportBacklog;
import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.report.reportbacklog.IReportBacklogService;
import bussinesslayer.service.report.reportbacklog.ReportBacklogService;
import bussinesslayer.service.sapce.backog.BacklogService;
import bussinesslayer.service.sapce.backog.IBacklogService;
import bussinesslayer.service.sapce.task.ITaskService;
import bussinesslayer.service.sapce.task.TaskService;
import bussinesslayer.service.user.IUserService;

import java.time.LocalDate;
import java.time.LocalTime;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class BacklogManagerMenu {
    // -------------------- Properties ------------------------
    private final IBacklogService serviceBacklog = new BacklogService();
    private final IReportBacklogService serviceReportBacklog  = new ReportBacklogService();
    private int projectId;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        CREATE_TASK_IN_BACKLOG,
        UPDATE_TASK_IN_BACKLOG,
        DELETE_TASK_IN_BACKLOG,
        VIEW_ALL_TASK_IN_BACKLOG,
        VIEW_REPORT_BACKLOG,
        UPDATE_REPORT_BACKLOG
    }
    // -------------------- Constructor ------------------------
    public BacklogManagerMenu(int projectId) throws Exception {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------

    // -------------------- Methods ------------------------

    private void checkBacklogNone() throws Exception {
        if (serviceBacklog.getAll().size() == 0) {
            printValueln("Backlog is empty.");
            serviceBacklog.create(new Backlog(projectId));
        }
    }

    public void processMenuForBacklogManager() throws Exception {
        boolean exit = false;
        checkBacklogNone();

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
                        case CREATE_TASK_IN_BACKLOG -> this.createTask();
                        case UPDATE_TASK_IN_BACKLOG -> this.updateTask();
                        case DELETE_TASK_IN_BACKLOG -> this.deleteTask();
                        case VIEW_ALL_TASK_IN_BACKLOG -> this.viewAllTask();
                        case VIEW_REPORT_BACKLOG -> this.viewReportBacklog();
                        case UPDATE_REPORT_BACKLOG -> this.updateReportBacklog();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createTask() throws Exception {
        String name = readString("Name: ");
        String description = readString("Description: ");
        LocalDate startDate = readLocalDate("Start date: ");
        LocalDate endDate = readLocalDate("End date: ");
        int memberId = -1;
        int sprintId = -1;
        Task task = new Task(name, description, startDate, endDate, memberId, sprintId);
        serviceBacklog.createTaskBacklog(task);
    }
    private void updateTask() throws Exception {
        int taskId = readInt("Task ID: ");
        String name = readString("Name: ");
        String description = readString("Description: ");
        LocalDate startDate = readLocalDate("Start date: ");
        LocalDate endDate = readLocalDate("End date: ");
        Task task = new Task(name, description, startDate, endDate);
        serviceBacklog.updateTaskBacklog(task, taskId);
    }
    private void deleteTask() throws Exception {
        int taskId = readInt("Task ID: ");
        serviceBacklog.deleteTaskBacklog(taskId);
    }
    private void viewAllTask() throws Exception {
        serviceBacklog.viewAllTaskBacklog(projectId);
    }
    private void viewReportBacklog() throws Exception {
        serviceReportBacklog.viewReport(projectId);
    }
    private void updateReportBacklog() throws Exception {
        int backlogId = readInt("Backlog ID: ");
        String description = readString("Description: ");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        ReportBacklog reportBacklog = new ReportBacklog(time, date, description, backlogId);
        serviceReportBacklog.updateReportBacklog(reportBacklog);
    }
}

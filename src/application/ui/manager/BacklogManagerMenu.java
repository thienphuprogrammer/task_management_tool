package application.ui.manager;

import bussinesslayer.entity.report.ReportBacklog;
import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Task;
import bussinesslayer.service.report.reportbacklog.IReportBacklogService;
import bussinesslayer.service.report.reportbacklog.ReportBacklogService;
import bussinesslayer.service.sapce.backog.BacklogService;
import bussinesslayer.service.sapce.backog.IBacklogService;
import bussinesslayer.service.sapce.task.ITaskService;
import bussinesslayer.service.sapce.task.TaskService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class BacklogManagerMenu {
    // -------------------- Properties ------------------------
    private final IBacklogService serviceBacklog = new BacklogService();
    private final IReportBacklogService serviceReportBacklog  = new ReportBacklogService();
    private final ITaskService serviceTask = new TaskService();
    private int projectId;
    private int backlogId;
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
            backlogId = serviceBacklog.getAll().get(0).getId();
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
    private void createTask()  {
        try {
            String name = readString("Name: ");
            String description = readString("Description: ");
            LocalDate startDate = readLocalDate("Start date: ");
            LocalDate endDate = readLocalDate("End date: ");
            int memberId = -1;
            int sprintId = -1;
            Task task = new Task(name, description, startDate, endDate, memberId, sprintId, backlogId);
            serviceTask.create(task);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void updateTask() {
        try {
            int taskId = readInt("Task ID: ");
            Task task = serviceTask.getById(taskId);
            if (task.getBacklogId() == backlogId) {
                task.setName(readString("Name: "));
                task.setDescription(readString("Description: "));
                task.setStartDate(readLocalDate("Start date: "));
                task.setEndDate(readLocalDate("End date: "));
                serviceTask.update(task);
            } else {
                printValueln("Task is not in this backlog.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void deleteTask() throws Exception {
        try {
            int taskId = readInt("Task ID: ");
            Task task = serviceTask.getById(taskId);
            if (task.getBacklogId() == backlogId) {
                serviceTask.delete(taskId);
            } else {
                printValueln("Task is not in this backlog.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllTask() {
        try {
            List<Task> list = serviceTask.getAllTask(backlogId);
            for (Task task : list) {
                printLineSeparate();
                printValue(("Task ID: " + task.getId() + " ".repeat(40 - String.valueOf(task.getId()).length()) + "|"));
                printValue("Name: " + task.getName() + " ".repeat(40 - String.valueOf(task.getName()).length()) + "|");
                printValue("Description: " + task.getDescription() + " ".repeat(36 - String.valueOf(task.getDescription()).length()) + "|");
                printValue("Start date: " + task.getStartDate() + " ".repeat(36 - String.valueOf(task.getStartDate()).length()) + "|");
                printValue("End date: " + task.getEndDate() + " ".repeat(36 - String.valueOf(task.getEndDate()).length()) + "|");
                printValue("Status: " + task.getStatus() + " ".repeat(36 - String.valueOf(task.getStatus()).length()) + "|");
                printValue("Member ID: " + task.getMemberId() + " ".repeat(36 - String.valueOf(task.getMemberId()).length()) + "|");
                printValue("Sprint ID: " + task.getSprintId() + " ".repeat(36 - String.valueOf(task.getSprintId()).length()) + "|");
                printLineSeparate();
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewReportBacklog() throws Exception {
        try {
            List<ReportBacklog> list = serviceReportBacklog.getReport(backlogId);
            for (ReportBacklog reportBacklog : list) {
                printLineSeparate();
                printValue(("Report ID: " + reportBacklog.getId() + " ".repeat(40 - String.valueOf(reportBacklog.getId()).length()) + "|"));
                printValue("Description: " + reportBacklog.getDescription() + " ".repeat(36 - String.valueOf(reportBacklog.getDescription()).length()) + "|");
                printValue("Date: " + reportBacklog.getDate() + " ".repeat(36 - String.valueOf(reportBacklog.getDate()).length()) + "|");
                printValue("Time: " + reportBacklog.getTime() + " ".repeat(36 - String.valueOf(reportBacklog.getTime()).length()) + "|");
                printValue("backlog ID: " + reportBacklog.getBacklogId() + " ".repeat(36 - String.valueOf(reportBacklog.getBacklogId()).length()) + "|");
                printLineSeparate();
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void updateReportBacklog() {
        try {
            int backlogId = readInt("Backlog ID: ");
            String description = readString("Description: ");
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            ReportBacklog reportBacklog = new ReportBacklog(time, date, description, backlogId);
            serviceReportBacklog.updateReportBacklog(reportBacklog);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

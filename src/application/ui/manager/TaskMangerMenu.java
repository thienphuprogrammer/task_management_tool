package application.ui.manager;

import bussinesslayer.entity.report.ReportTask;
import bussinesslayer.entity.space.Task;
import bussinesslayer.service.report.reporttask.IReportTaskService;
import bussinesslayer.service.report.reporttask.ReportTaskService;
import bussinesslayer.service.sapce.task.ITaskService;
import bussinesslayer.service.sapce.task.TaskService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class TaskMangerMenu {
    // -------------------- Properties ------------------------
    private final ITaskService serviceTask = new TaskService();
    private final IReportTaskService reportTaskService = new ReportTaskService();
    private int sprintId;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        CREATE_TASK,
        UPDATE_TASK,
        DELETE_TASK,
        VIEW_TASK,
        VIEW_TASK_PROGRESS_TRACKING,
        ASSIGN_TASK_TO_MEMBER,
        REASSIGN_TASK_TO_MEMBER,
        CREATE_REPORT,
        VIEW_REPORT,
    }
    //  ------------------- Constructor ------------------------


    public TaskMangerMenu(int sprintId) throws Exception {
        this.sprintId = sprintId;
    }

    //  ------------------- Getters and Setters ------------------------

    //  ------------------- Methods ------------------------
    public void processMenuForTaskManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Task Manager Menu");
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
                        case CREATE_TASK -> this.createTask();
                        case UPDATE_TASK -> this.updateTask();
                        case DELETE_TASK -> this.deleteTask();
                        case VIEW_TASK -> this.viewTask();
                        case VIEW_TASK_PROGRESS_TRACKING -> this.viewTaskProgressTracking();
                        case ASSIGN_TASK_TO_MEMBER -> this.assignTaskToMember();
                        case REASSIGN_TASK_TO_MEMBER -> this.reassignTaskToMember();
                        case CREATE_REPORT -> this.createReport();
                        case VIEW_REPORT -> this.viewReport();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createTask() throws Exception {
        try {
            String name = readString("Enter task name: ");
            String description = readString("Enter task description: ");
            LocalDate startDate = readLocalDate("Enter task start date: ");
            LocalDate endDate = readLocalDate("Enter task end date: ");
            Task task = new Task(name, description, startDate, endDate, sprintId);
            serviceTask.create(task);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void updateTask() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = serviceTask.getById(taskId);
            if (task.getSprintId() == sprintId) {
                task.setName(readString("Enter task name: "));
                task.setDescription(readString("Enter task description: "));
                task.setStartDate(readLocalDate("Enter task start date: "));
                task.setEndDate(readLocalDate("Enter task end date: "));
                task.setSprintId(readInt("Enter task sprint id: "));
            } else {
                printValueln("You are not manager of this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void deleteTask() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            if (serviceTask.getById(taskId).getSprintId() == sprintId) {
                serviceTask.delete(taskId);
            } else {
                printValueln("You are not manager of this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewTask() throws Exception {
        try {
            List <Task> list = serviceTask.getAllTasksMamager(sprintId);
            for (Task task1 : list) {
                printValue("id: " + task1.getId() + " ".repeat(40 - String.valueOf(task1.getId()).length()) + "|");
                printValue("name: " + task1.getName() + " ".repeat(40 - String.valueOf(task1.getName()).length()) + "|");
                printValue("start date: " + task1.getStartDate() + " ".repeat(40 - String.valueOf(task1.getStartDate()).length()) + "|");
                printValue("end date: " + task1.getEndDate() + " ".repeat(40 - String.valueOf(task1.getEndDate()).length()) + "|");
                printValue("description: " + task1.getDescription() + " ".repeat(40 - String.valueOf(task1.getDescription()).length()) + "|");
                printValueln("sprint id: " + task1.getSprintId() + " ".repeat(40 - String.valueOf(task1.getSprintId()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewTaskProgressTracking() throws Exception {
        try {
            List<Task> list = serviceTask.getTaskProgress(sprintId);
            for (Task task : list) {
                printValue("id: " + task.getId() + " ".repeat(40 - String.valueOf(task.getId()).length()) + "|");
                printValue("name: " + task.getName() + " ".repeat(40 - String.valueOf(task.getName()).length()) + "|");
                printValue("start date: " + task.getStartDate() + " ".repeat(40 - String.valueOf(task.getStartDate()).length()) + "|");
                printValue("end date: " + task.getEndDate() + " ".repeat(40 - String.valueOf(task.getEndDate()).length()) + "|");
                printValue("description: " + task.getDescription() + " ".repeat(40 - String.valueOf(task.getDescription()).length()) + "|");
                printValueln("sprint id: " + task.getSprintId() + " ".repeat(40 - String.valueOf(task.getSprintId()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }

    }
    private void assignTaskToMember() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = serviceTask.getById(taskId);
            if (task.getSprintId() == sprintId) {
                int memberId = readInt("Enter member id: ");
                task.setMemberId(memberId);
                serviceTask.update(task);
                printValueln("Task assigned to member.");
            } else {
                printValueln("You are not manager of this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void reassignTaskToMember() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = serviceTask.getById(taskId);
            if (task.getSprintId() == sprintId) {
                int memberId = readInt("Enter member id: ");
                task.setMemberId(memberId);
                serviceTask.update(task);
                printValueln("Task re-assigned to member.");
                printValueln("Task re-assigned to member.");
            } else {
                printValueln("You are not manager of this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void createReport() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = serviceTask.getById(taskId);
            if (task.getSprintId() == sprintId) {
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now();
                String description = readString("Description: ");
                ReportTask reportTask = new ReportTask(time, date, description, taskId);
                reportTaskService.create(reportTask);
            } else {
                printValueln("You are not manager of this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewReport() throws Exception {
        try {
            List<ReportTask> reportTaskList = reportTaskService.getReports(sprintId);
            for (ReportTask reportTask : reportTaskList) {
                printValue("Task id: " + reportTask.getTaskId() + " ".repeat(40 - String.valueOf(reportTask.getTaskId()).length()) + "|");
                printValue("description: " + reportTask.getDescription() + " ".repeat(40 - String.valueOf(reportTask.getDescription()).length()) + "|");
                printValue("time: " + reportTask.getTime() + " ".repeat(40 - String.valueOf(reportTask.getTime()).length()) + "|");
                printValueln("date: " + reportTask.getDate() + " ".repeat(40 - String.valueOf(reportTask.getDate()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

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
        VIEW_ALL_TASKS,
        ASSIGN_TASK_TO_MEMBER,
        REASSIGN_TASK_TO_MEMBER,
        CREATE_REPORT,
        VIEW_ALL_REPORTS,
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
                        case VIEW_ALL_TASKS -> this.viewAllTasks();
                        case ASSIGN_TASK_TO_MEMBER -> this.assignTaskToMember();
                        case REASSIGN_TASK_TO_MEMBER -> this.reassignTaskToMember();
                        case CREATE_REPORT -> this.createReport();
                        case VIEW_ALL_REPORTS -> this.viewAllReports();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    /*
     * create task
     * check validation
     * check start date < end date
     */
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

    /*
     * update task
     * check task_id exist
     * check task exist in sprint
     * check start date < end date
     * check validation
     * press enter to keep old info
     */
    private void updateTask() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = serviceTask.getById(taskId);
            if (task.getSprintId() == sprintId) {
                task.setName(readString("Enter task name: "));
                task.setDescription(readString("Enter task description: "));
                task.setStartDate(readLocalDate("Enter task start date: "));
                task.setEndDate(readLocalDate("Enter task end date: "));
            } else {
                printValueln("You are not manager of this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * delete task
     * check task_id exist
     * check task exist in sprint
     * ask user to confirm
     * delete all report has task_id of this task
     */
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

    /*
     * view all tasks
     * check list is null
     */
    private void viewAllTasks() throws Exception {
        try {
            List <Task> list = serviceTask.getAllTasks(sprintId);
            for (Task task1 : list) {
                printValue("id: " + task1.getId() + " ".repeat(10 - String.valueOf(task1.getId()).length()) + "|");
                printValue("name: " + task1.getName() + " ".repeat(20 - String.valueOf(task1.getName()).length()) + "|");
                printValue("start date: " + task1.getStartDate() + " ".repeat(10 - String.valueOf(task1.getStartDate()).length()) + "|");
                printValue("end date: " + task1.getEndDate() + " ".repeat(10 - String.valueOf(task1.getEndDate()).length()) + "|");
                printValue("description: " + task1.getDescription() + " ".repeat(40 - String.valueOf(task1.getDescription()).length()) + "|");
                printValueln("sprint id: " + task1.getSprintId() + " ".repeat(10 - String.valueOf(task1.getSprintId()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * assign task to member
     * check task_id exist
     * check task exist in sprint
     * check member exist
     * check member who is in project
     * check task is assigned to member ? show member : ask user to confirm
     */
    private void assignTaskToMember() {
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

    /*
     * reassign task to member
     * check task_id exist
     * check task exist in sprint
     * check member exist
     * check member who is in project
     * check new project
     */
    private void reassignTaskToMember() {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = serviceTask.getById(taskId);
            if (task.getSprintId() == sprintId) {
                int memberId = readInt("Enter member id: ");
                task.setMemberId(memberId);
                serviceTask.update(task);
                printValueln("Task re-assigned to member.");
            } else {
                printValueln("You are not manager of this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * create report
     * check task_id exist
     * check task exist in sprint
     * check check validation
     */
    private void createReport() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = serviceTask.getById(taskId);
            if (task.getSprintId() == sprintId) {
                String description = readString("Description: ");
                ReportTask reportTask = new ReportTask(description, taskId);
                reportTaskService.create(reportTask);
            } else {
                printValueln("You are not manager of this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    /*
     * view all reports
     * check list is null
     */
    private void viewAllReports() throws Exception {
        try {
            List<ReportTask> reportTaskList = reportTaskService.getReportsBySprintId(sprintId);
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

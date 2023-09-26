package application.ui.member;

import bussinesslayer.entity.report.ReportTask;
import bussinesslayer.entity.space.Task;
import bussinesslayer.service.report.reporttask.IReportTaskService;
import bussinesslayer.service.report.reporttask.ReportTaskService;
import bussinesslayer.service.sapce.task.ITaskService;
import bussinesslayer.service.sapce.task.TaskService;

import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;


public class TaskMemberMenu {
    // -------------------- Properties ------------------------
    private final ITaskService taskService = new TaskService();
    private final IReportTaskService reportTaskService = new ReportTaskService();
    private int sprintId;
    public int memberId;
    public enum CHOICE_TASK_MEMBER_MENU {
        EXIT,
        SUBMIT_TASK,
        VIEW_ALL_MY_TASK,
        VIEW_ALL_TASK,
        VIEW_REPORT_TASK,
    }
    // -------------------- Constructor ------------------------

    public TaskMemberMenu(int sprintId, int memberId) throws Exception {
        this.sprintId = sprintId;
        this.memberId = memberId;
    }

    // -------------------- Getters and Setters ------------------------
    // -------------------- Methods ------------------------
    public void processMenuForTaskMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Task Member Menu");
            printValueMenu("Member\\Project\\Sprint\\Task");
            for (CHOICE_TASK_MEMBER_MENU choice : CHOICE_TASK_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_TASK_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_TASK_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case VIEW_REPORT_TASK -> this.viewReportTask();
                        case VIEW_ALL_TASK -> this.viewAllTask();
                        case SUBMIT_TASK -> this.submitTask();
                        case VIEW_ALL_MY_TASK -> this.viewAllMyTask();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void viewReportTask() {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = taskService.getById(taskId);
            if (task != null && task.getSprintId() == this.sprintId) {
                List<ReportTask> reportTaskList = reportTaskService.getReports(taskId);
                for (ReportTask reportTask : reportTaskList) {
                    printValue("Report task id: " + reportTask.getId() + " ".repeat(10 - String.valueOf(reportTask.getId()).length()) + "|");
                    printValue("Report time: " + reportTask.getTime() + " ".repeat(20 - String.valueOf(reportTask.getTime()).length()) + "|");
                    printValue("Report date: " + reportTask.getDate() + " ".repeat(20 - String.valueOf(reportTask.getDate()).length()) + "|");
                    printValueln("Description: " + reportTask.getDescription() + " ".repeat(40 - String.valueOf(reportTask.getDescription()).length()) + "|");
                }
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllMyTask() {
        try {
            List<Task> taskList = taskService.getAllMyTaskMember(sprintId, memberId);
            for (Task task : taskList) {
                printValue("Task id: " + task.getId() + " ".repeat(40 - String.valueOf(task.getId()).length()) + "|");
                printValue("Task name: " + task.getName() + " ".repeat(40 - String.valueOf(task.getName()).length()) + "|");
                printValue("Task description: " + task.getDescription() + " ".repeat(40 - String.valueOf(task.getDescription()).length()) + "|");
                printValue("Task start date: " + task.getStartDate() + " ".repeat(40 - String.valueOf(task.getStartDate()).length()) + "|");
                printValue("Task end date: " + task.getEndDate() + " ".repeat(40 - String.valueOf(task.getEndDate()).length()) + "|");
                printValueln("Task status: " + task.getStatus() + " ".repeat(40 - String.valueOf(task.getStatus()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllTask() {
        try {
            List<Task> taskList = taskService.getAllTasks(sprintId);
            for (Task task : taskList) {
                printValue("Task id: " + task.getId() + " ".repeat(40 - String.valueOf(task.getId()).length()) + "|");
                printValue("Task name: " + task.getName() + " ".repeat(40 - String.valueOf(task.getName()).length()) + "|");
                printValue("Task description: " + task.getDescription() + " ".repeat(40 - String.valueOf(task.getDescription()).length()) + "|");
                printValue("Task start date: " + task.getStartDate() + " ".repeat(40 - String.valueOf(task.getStartDate()).length()) + "|");
                printValue("Task end date: " + task.getEndDate() + " ".repeat(40 - String.valueOf(task.getEndDate()).length()) + "|");
                printValueln("Task status: " + task.getStatus() + " ".repeat(40 - String.valueOf(task.getStatus()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void submitTask() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = taskService.getById(taskId);
            if (task != null && task.getSprintId() == this.sprintId) {
                taskService.submitTask(taskId);
            } else {
                printValueln("Task not found.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

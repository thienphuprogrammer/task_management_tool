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
        SUBTASK_MEMBER
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
                        case SUBTASK_MEMBER -> this.processMenuForSubtaskManager();
                        case VIEW_ALL_MY_TASK -> this.viewAllMyTask();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void viewReportTask()  {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = taskService.getById(taskId);
            if (task != null && task.getSprintId() == this.sprintId) {
                List<ReportTask> reportTaskList = reportTaskService.getReports(taskId);
                for (ReportTask reportTask : reportTaskList) {
                    printValue(reportTask.toString());
                }
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllMyTask() {
        try {
            List<Task> taskList = taskService.getAllMyTaskMember(sprintId, memberId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllTask() {
        try {
            List<Task> taskList = taskService.getAllTask(sprintId);
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
    private void processMenuForSubtaskManager() throws Exception {
        try {
            int taskId = readInt("Enter task id: ");
            Task task = taskService.getById(taskId);
            if (task != null && task.getSprintId() == this.sprintId) {
                SubtaskMemberMenu subtaskMemberMenu = new SubtaskMemberMenu(taskId, memberId);
                subtaskMemberMenu.processMenuForSubtaskMember();
            } else {
                printValueln("Task not found.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

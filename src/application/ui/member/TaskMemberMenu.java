package application.ui.member;

import bussinesslayer.service.report.reporttask.IReportTaskService;
import bussinesslayer.service.report.reporttask.ReportTaskService;
import bussinesslayer.service.sapce.task.ITaskService;
import bussinesslayer.service.sapce.task.TaskService;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;


public class TaskMemberMenu {
    // -------------------- Properties ------------------------
    private final ITaskService taskService = new TaskService();
    private final IReportTaskService reportTaskService = new ReportTaskService();
    private int sprintId;
    public enum CHOICE_TASK_MEMBER_MENU {
        EXIT,
        SUBMIT_TASK,
        VIEW_MY_TASK,
        VIEW_ALL_TASK,
        VIEW_REPORT_TASK,
        SUBTASK_MEMBER
    }
    // -------------------- Constructor ------------------------

    public TaskMemberMenu(int sprintId) throws Exception {
        this.sprintId = sprintId;
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
                        case VIEW_MY_TASK -> this.viewMyTask();
                        case VIEW_ALL_TASK -> this.viewAllTask();
                        case SUBMIT_TASK -> this.submitTask();
                        case SUBTASK_MEMBER -> this.processMenuForSubtaskManager();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void viewReportTask() throws Exception {
        int taskId = readInt("Enter task id: ");
        reportTaskService.viewReportSprint(taskId, sprintId);
    }
    private void viewMyTask() throws Exception {
        int taskId = readInt("Enter task id: ");
        taskService.viewTaskSprint(taskId, sprintId);
    }
    private void viewAllTask() {
        taskService.viewAllTaskProject(sprintId);
    }
    private void submitTask() throws Exception {
        int taskId = readInt("Enter task id: ");
        taskService.submitTask(taskId);
    }
    private void processMenuForSubtaskManager() throws Exception {
        SubtaskMemberMenu subtaskMemberMenu = new SubtaskMemberMenu(sprintId);
        subtaskMemberMenu.processMenuForSubtaskMember();
    }
}

package application.ui.member;

import bussinesslayer.service.report.reportsubtask.IReportSubtaskService;
import bussinesslayer.service.report.reportsubtask.ReportSubtaskService;
import bussinesslayer.service.sapce.subtask.ISubtaskService;
import bussinesslayer.service.sapce.subtask.SubtaskService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class SubtaskMemberMenu {
    private final ISubtaskService subtaskService = new SubtaskService();
    private final IReportSubtaskService reportSubtaskService = new ReportSubtaskService();
    private int taskId;
    private int memberId;
    public enum CHOICE_SUBTASK_MEMBER_MENU {
        EXIT,
        SUBMIT_SUBTASK,
        VIEW_MY_SUBTASK,
        VIEW_ALL_MY_SUBTASK,
        VIEW_ALL_SUBTASK,
        VIEW_REPORT_SUBTASK
    }
    // -------------------- Constructor ------------------------

    public SubtaskMemberMenu(int taskId, int memberId) throws Exception {
        this.taskId = taskId;
        this.memberId = memberId;
    }

    // -------------------- Getters and Setters ------------------------
    // -------------------- Methods ------------------------
    public void processMenuForSubtaskMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Subtask Member Menu");
            printValueMenu("Member\\Project\\Sprint\\Task\\Subtask");
            for (CHOICE_SUBTASK_MEMBER_MENU choice : CHOICE_SUBTASK_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_SUBTASK_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_SUBTASK_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case VIEW_REPORT_SUBTASK -> this.viewReportSubtask();
                        case VIEW_MY_SUBTASK -> this.viewMySubtask();
                        case VIEW_ALL_SUBTASK -> this.viewAllSubtask();
                        case SUBMIT_SUBTASK -> this.submitSubtask();
                        case VIEW_ALL_MY_SUBTASK -> this.viewAllMySubtask();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void viewReportSubtask() throws Exception {
        int subtaskId = readInt("Enter subtask id: ");
        reportSubtaskService.viewReport(subtaskId);
    }
    private void viewMySubtask() throws Exception {
        try {
            int subtaskId = readInt("Enter subtask id: ");
            subtaskService.getSubtaskProject(subtaskId, taskId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllMySubtask() throws Exception {
        try {
            subtaskService.getAllMySubtaskProject(taskId, memberId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllSubtask() {
        subtaskService.viewAllSubtaskProject(taskId);
    }
    private void submitSubtask(){
        try {
            int taskId = readInt("Enter task id: ");
            if (taskId == this.taskId) {
                int subtaskId = readInt("Enter subtask id: ");
                subtaskService.submitSubtask(subtaskId);
            } else {
                printValueln("Invalid task id.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

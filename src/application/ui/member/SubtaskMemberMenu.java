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
    public enum CHOICE_SUBTASK_MEMBER_MENU {
        EXIT,
        COMPLETE_SUBTASK,
        VIEW_MY_SUBTASK,
        VIEW_ALL_SUBTASK,
        VIEW_REPORT_SUBTASK
    }
    // -------------------- Constructor ------------------------

    public SubtaskMemberMenu(int taskId) throws Exception {
        this.taskId = taskId;
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
                        case COMPLETE_SUBTASK -> this.completeSubtask();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void viewReportSubtask() {

    }
    private void viewMySubtask() {

    }
    private void viewAllSubtask() {

    }
    private void completeSubtask() {

    }
}

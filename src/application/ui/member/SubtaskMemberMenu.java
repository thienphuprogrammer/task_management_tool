package application.ui.member;

import bussinesslayer.entity.space.Subtask;
import bussinesslayer.service.report.reportsubtask.IReportSubtaskService;
import bussinesslayer.service.report.reportsubtask.ReportSubtaskService;
import bussinesslayer.service.sapce.subtask.ISubtaskService;
import bussinesslayer.service.sapce.subtask.SubtaskService;

import java.util.List;

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
        try {
            int subtaskId = readInt("Enter subtask id: ");
            Subtask subtask = subtaskService.getById(subtaskId);
            if (subtask != null && subtask.getTaskId() == this.taskId) {
                reportSubtaskService.getReport(subtaskId);
            } else {
                printValueln("Subtask is not in this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllMySubtask() throws Exception {
        try {
            List<Subtask> subtaskList = subtaskService.getAllMySubtaskProject(taskId, memberId);
            for (Subtask subtask : subtaskList) {
                printValue(String.valueOf(subtask.getId()));
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllSubtask() {
        try {
            subtaskService.getAllSubtask(taskId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
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

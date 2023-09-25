package application.ui.manager;

import bussinesslayer.entity.report.ReportSubtask;
import bussinesslayer.entity.space.Subtask;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.report.reportsubtask.IReportSubtaskService;
import bussinesslayer.service.report.reportsubtask.ReportSubtaskService;
import bussinesslayer.service.sapce.subtask.ISubtaskService;
import bussinesslayer.service.sapce.subtask.SubtaskService;
import bussinesslayer.service.user.IUserService;

import java.time.LocalDate;
import java.time.LocalTime;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class SubtaskManagerMenu {
    // -------------------- Properties ------------------------
    private final ISubtaskService subtaskManager = new SubtaskService();
    private final IReportSubtaskService reportSubtaskService = new ReportSubtaskService();
    private int taskId;
    public enum CHOICE_SUBTASK_MANAGER_MENU {
        EXIT,
        CREATE_SUBTASK,
        UPDATE_SUBTASK,
        DELETE_SUBTASK,
        VIEW_SUBTASK,
        VIEW_ALL_MY_SUBTASK,
        VIEW_ALL_SUBTASK,
        ASSIGN_SUBTASK_TO_MEMBER,
        REASSIGN_SUBTASK_TO_MEMBER,
        CREATE_REPORT,
        VIEW_REPORT
    }
    //  ------------------- Constructor ------------------------

    public SubtaskManagerMenu(int taskId) throws Exception {
        this.taskId = taskId;
    }

    // -------------------- Getters and Setters ------------------------
    // -------------------- Methods ------------------------

    public void processMenuForSubtaskManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Subtask Manager Menu");
            for (CHOICE_SUBTASK_MANAGER_MENU choice : CHOICE_SUBTASK_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_SUBTASK_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_SUBTASK_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_SUBTASK -> this.createSubtask();
                        case UPDATE_SUBTASK -> this.updateSubtask();
                        case DELETE_SUBTASK -> this.deleteSubtask();
                        case VIEW_SUBTASK -> this.viewSubtask();
                        case ASSIGN_SUBTASK_TO_MEMBER -> this.assignSubtaskToMember();
                        case REASSIGN_SUBTASK_TO_MEMBER -> this.reassignSubtaskToMember();
                        case CREATE_REPORT -> this.createReport();
                        case VIEW_REPORT -> this.viewReport();
                        case VIEW_ALL_MY_SUBTASK -> this.viewAllMySubtask();
                        case VIEW_ALL_SUBTASK -> this.viewAllSubtask();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void assignSubtaskToMember() {
        try {
            int memberId = readInt("Enter member id: ");
            int subtaskId = readInt("Enter subtask id: ");
            subtaskManager.assignSubtaskToMember(subtaskId, memberId, taskId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void reassignSubtaskToMember() {
        try {
            int memberId = readInt("Enter member id: ");
            int subtaskId = readInt("Enter subtask id: ");
            subtaskManager.reassignSubtaskToMember(subtaskId, memberId, taskId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewSubtask() {
        try {
            int subtaskId = readInt("Enter subtask id: ");
            subtaskManager.getSubtaskProject(subtaskId, taskId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    private void viewAllMySubtask() {
        try {
            int memberId = readInt("Enter member id: ");
            subtaskManager.getAllMySubtask(memberId, taskId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllSubtask() {
        try {
            subtaskManager.getAllSubtask(taskId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void createSubtask() throws Exception {
        try {
            String name = readString("Name: ");
            String description = readString("Description: ");
            LocalDate startDate = readLocalDate("Start date: ");
            LocalDate endDate = readLocalDate("End date: ");
            int status = 0;
            Subtask subtask = new Subtask(name, description, startDate, endDate, taskId, status);
            subtaskManager.create(subtask);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }

    }
    private void updateSubtask() {
        try {
            int subtaskId = readInt("Enter subtask id: ");
            Subtask subtask = subtaskManager.getById(subtaskId);
            if (subtask.getTaskId() == taskId) {
                subtask.setName(readString("Name: "));
                subtask.setDescription(readString("Description: "));
                subtask.setStartDate(readLocalDate("Start date: "));
                subtask.setEndDate(readLocalDate("End date: "));
                subtaskManager.update(subtask);
            } else {
                printValueln("Subtask is not in this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void deleteSubtask() throws Exception {
        try {
            int subtaskId = readInt("Enter subtask id: ");
            if (subtaskManager.getById(subtaskId).getTaskId() == taskId) {
                subtaskManager.delete(subtaskId);
            } else {
                printValueln("Subtask is not in this task.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void createReport() throws Exception {
        int subtaskId = readInt("Enter subtask id: ");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String description = readString("Description: ");
        ReportSubtask reportSubtask = new ReportSubtask(time, date, description, subtaskId);
        reportSubtaskService.createReport(reportSubtask);
    }
    private void viewReport() {
        reportSubtaskService.viewReport(taskId);
    }
}

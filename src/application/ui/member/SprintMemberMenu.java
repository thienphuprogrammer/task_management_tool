package application.ui.member;

import bussinesslayer.service.report.reportsprint.IReportSprintService;
import bussinesslayer.service.report.reportsprint.ReportSprintService;
import bussinesslayer.service.sapce.sprint.ISprintService;
import bussinesslayer.service.sapce.sprint.SprintService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class SprintMemberMenu {
    // -------------------- Properties ------------------------
    private final ISprintService sprintService = new SprintService();
    private final IReportSprintService reportSprintService = new ReportSprintService();
    private int projectId;
    public enum CHOICE_SPRINT_MEMBER_MENU {
        EXIT,
        VIEW_MY_SPRINT,
        VIEW_ALL_SPRINT,
        TASK_MEMBER
    }
    // -------------------- Constructor ------------------------

    public SprintMemberMenu(int projectId) throws Exception {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------
    // -------------------- Methods ------------------------
    public void processMenuForSprintMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Sprint Member Menu");
            printValueMenu("Member\\Project\\Sprint");
            for (CHOICE_SPRINT_MEMBER_MENU choice : CHOICE_SPRINT_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_SPRINT_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_SPRINT_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case VIEW_MY_SPRINT -> this.viewMySprint();
                        case VIEW_ALL_SPRINT -> this.viewAllSprint();
                        case TASK_MEMBER -> this.processMenuForTaskMember();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void viewMySprint() throws Exception {
        int sprintId = readInt("Enter sprint id: ");
        sprintService.viewSprintProject(sprintId, projectId);
    }
    private void viewAllSprint() {
        sprintService.viewAllSprintProject(projectId);
    }
    private void processMenuForTaskMember() throws Exception {
        TaskMemberMenu taskMemberMenu = new TaskMemberMenu(projectId);
        taskMemberMenu.processMenuForTaskMember();
    }
}

package application.ui.member;

import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.report.reportsprint.IReportSprintService;
import bussinesslayer.service.report.reportsprint.ReportSprintService;
import bussinesslayer.service.sapce.sprint.ISprintService;
import bussinesslayer.service.sapce.sprint.SprintService;

import java.util.List;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class SprintMemberMenu {
    // -------------------- Properties ------------------------
    private final ISprintService sprintService = new SprintService();
    private final IReportSprintService reportSprintService = new ReportSprintService();
    private int projectId;
    private int memberId;
    public enum CHOICE_SPRINT_MEMBER_MENU {
        EXIT,
        VIEW_MY_SPRINT,
        VIEW_ALL_SPRINT,
        TASK_MEMBER
    }
    // -------------------- Constructor ------------------------

    public SprintMemberMenu(int projectId, int memberId) throws Exception {
        this.projectId = projectId;
        this.memberId = memberId;
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
            waitForInput();
        }
    }
    private void viewMySprint() throws Exception {
        try {
            List<Sprint> sprintList = sprintService.getSprintMember(projectId, memberId);
            for (Sprint sprint : sprintList) {
                printValue("Sprint id: " + sprint.getId() + " ".repeat(40 - String.valueOf(sprint.getId()).length()) + "|");
                printValue("Sprint name: " + sprint.getName() + " ".repeat(40 - String.valueOf(sprint.getName()).length()) + "|");
                printValue("Sprint description: " + sprint.getDescription() + " ".repeat(40 - String.valueOf(sprint.getDescription()).length()) + "|");
                printValue("Sprint start date: " + sprint.getStartDate() + " ".repeat(40 - String.valueOf(sprint.getStartDate()).length()) + "|");
                printValueln("Sprint end date: " + sprint.getEndDate() + " ".repeat(40 - String.valueOf(sprint.getEndDate()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllSprint() {
        try {
            List<Sprint> sprintList = sprintService.getAllSprint(projectId);
            for (Sprint sprint : sprintList) {
                printValue("Sprint id: " + sprint.getId() + " ".repeat(40 - String.valueOf(sprint.getId()).length()) + "|");
                printValue("Sprint name: " + sprint.getName() + " ".repeat(40 - String.valueOf(sprint.getName()).length()) + "|");
                printValue("Sprint description: " + sprint.getDescription() + " ".repeat(40 - String.valueOf(sprint.getDescription()).length()) + "|");
                printValue("Sprint start date: " + sprint.getStartDate() + " ".repeat(40 - String.valueOf(sprint.getStartDate()).length()) + "|");
                printValueln("Sprint end date: " + sprint.getEndDate() + " ".repeat(40 - String.valueOf(sprint.getEndDate()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void processMenuForTaskMember() throws Exception {
        try {
            int sprintId = readInt("Enter sprint id: ");
            Sprint sprint = sprintService.getById(sprintId);
            if (sprint != null && sprint.getProjectId() == projectId) {
                TaskMemberMenu taskMemberMenu = new TaskMemberMenu(sprintId, memberId);
                taskMemberMenu.processMenuForTaskMember();
            } else {
                printValueln("Sprint not found.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}

package application.ui.member;

import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.sapce.sprint.ISprintService;
import bussinesslayer.service.sapce.sprint.SprintService;

import java.util.List;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class SprintMemberMenu {
    // -------------------- Properties ------------------------
    private final ISprintService sprintService = new SprintService();
    private int projectId;
    private int memberId;
    public enum CHOICE_SPRINT_MEMBER_MENU {
        EXIT,
        VIEW_ALL_MY_SPRINTS,
        VIEW_ALL_SPRINTS_IN_PROJECT,
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
                        case VIEW_ALL_MY_SPRINTS -> this.viewAllMySprints();
                        case VIEW_ALL_SPRINTS_IN_PROJECT -> this.viewAllSprints();
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

    /*
     * View my sprint
     * check list is null ?
     * get all sprint which member is assigned
     */
    private void viewAllMySprints() {
        try {
            List<Sprint> sprintList = sprintService.getAllSprintsInProjectOfMember(projectId, memberId);
            for (Sprint sprint : sprintList) {
                printValue("Sprint id: " + sprint.getId() + " ".repeat(10 - String.valueOf(sprint.getId()).length()) + "|");
                printValue("Sprint name: " + sprint.getName() + " ".repeat(20 - String.valueOf(sprint.getName()).length()) + "|");
                printValue("Sprint description: " + sprint.getDescription() + " ".repeat(30 - String.valueOf(sprint.getDescription()).length()) + "|");
                printValue("Sprint start date: " + sprint.getStartDate() + " ".repeat(10 - String.valueOf(sprint.getStartDate()).length()) + "|");
                printValueln("Sprint end date: " + sprint.getEndDate() + " ".repeat(10 - String.valueOf(sprint.getEndDate()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    /*
     * View all sprint
     * check list is null ?
     * get all sprint in project
     */
    private void viewAllSprints() {
        try {
            List<Sprint> sprintList = sprintService.getAllSprintsOfProject(projectId);
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
    /*
     * Process menu for task member
     * check sprintId exist
     * check sprint in project
     */
    private void processMenuForTaskMember() throws Exception {
        try {
            int sprintId = readInt("Enter sprint id: ");
            if(sprintService.getById(sprintId) == null) {
                printValue("This print id does not exist!");
                return;
            }
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
